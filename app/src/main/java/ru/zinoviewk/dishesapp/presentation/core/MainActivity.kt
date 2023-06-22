package ru.zinoviewk.dishesapp.presentation.core

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ru.zinoviewk.dishesapp.R
import java.util.concurrent.atomic.AtomicBoolean

fun Any.log(message: String) {
    Log.d("zinoviewktest", message)
}

class MainActivity : AppCompatActivity() {

    private val viewModel: CountDownViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this)[CountDownViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) CoroutineScope(Dispatchers.IO).launch {
            repeat(1000) {
                delay(100)
                log("Run $it, act ${this@MainActivity}")
            }
        }
//        launchWhenStarted {
//            viewModel.timerValue.observe { value ->
//                log("Timer value is $value")
//            }
//            viewModel.isFinished.observe { isFinished ->
//                if (isFinished) {
//                    Toast.makeText(this@MainActivity, "isFinished!", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startOrResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pause()
    }

}

suspend inline fun <T> Flow<T>.collect(
    scope: CoroutineScope,
    crossinline block: (T) -> Unit
) {
    scope.launch {
        this@collect.collectLatest { block(it) }
    }
}

class CountDownViewModel : ViewModel() {

    private val _timerValue = MutableStateFlow(10L)
    val timerValue = _timerValue.asStateFlow()

    val isFinished = MutableSharedFlow<Boolean>(
        replay = 0,
        extraBufferCapacity = 1,
        BufferOverflow.DROP_OLDEST
    )

    private val isRunning = AtomicBoolean(false)
    private var timerJob: Job? = null
    fun startOrResume() {
        if (isRunning.compareAndSet(false, true)) {
            timerJob = viewModelScope.launch(Dispatchers.IO) {
                while (isActive && timerValue.value > 0 && isRunning.get()) {
                    delay(1000)
                    _timerValue.update { value -> value - 1 }
                }
                isFinished.emit(timerValue.value == 0L)
            }
        }
    }

    fun pause() {
        timerJob?.cancel()
        isRunning.set(false)
    }
}

inline fun AppCompatActivity.launchWhenStarted(crossinline block: CoroutineScopeWrapper.() -> Unit) {
    this.lifecycleScope.launchWhenStarted {
        CoroutineScopeWrapper(this).block()
    }
}

class CoroutineScopeWrapper(
    val scope: CoroutineScope
) {
    inline fun <T> Flow<T>.observe(crossinline block: (T) -> Unit) {
        scope.launch {
            this@observe
                .catch { }
                .onEach { }
                .collect {
                    block(it)
                }
        }

    }
}

private fun Interceptor.Chain.proceedNewAuthorizedRequest(initialToken: String?): Response {
    TODO()
}

class AuthInterceptor(
    private val getToken: () -> String?,
    private val refreshToken: suspend () -> Unit,
    private val onRefreshTokenFailed: () -> Unit,
    private val isRequestRequiresAuthorization: (Request) -> Boolean
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val initialToken = getToken()
        val response = chain.proceedNewAuthorizedRequest(initialToken)
        if (response.code != 401 || !isRequestRequiresAuthorization(response.request)) {
            return response
        }

        synchronized(this) {
            response.close()
            val maybeNewToken = getToken() // check for concurrency issie
            val newResponse = if (initialToken != maybeNewToken) {
                chain.proceedNewAuthorizedRequest(maybeNewToken)
            } else {
                runBlocking { refreshToken() }
                chain.proceedNewAuthorizedRequest(getToken())
            }
            if (newResponse.code == 401) {
                onRefreshTokenFailed()
            }
            return newResponse
        }
    }
}

class AtomicAuthInterceptor(
    private val getToken: () -> String?,
    private val refreshToken: suspend () -> Unit,
    private val onRefreshTokenFailed: () -> Unit,
    private val isRequestRequiresAuthorization: (Request) -> Boolean
) : Interceptor {

    private val isRefreshing = AtomicBoolean(false)

    @Volatile
    private var isRefreshed = false

    override fun intercept(chain: Interceptor.Chain): Response {
        val initialToken = getToken()
        val response = chain.proceedNewAuthorizedRequest(initialToken)
        if (response.code != 401 || !isRequestRequiresAuthorization(response.request)) {
            return response
        }

        while(!isRefreshed) {
            if(isRefreshing.compareAndSet(false, true)) {
                runBlocking { refreshToken() }
                isRefreshed = true
            }
        }
        val newResponse = chain.proceedNewAuthorizedRequest(getToken())
        if (newResponse.code == 401) {
            onRefreshTokenFailed()
        }
        isRefreshing.set(false)
        return newResponse
    }
}