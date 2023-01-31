package ru.zinoviewk.dishesapp.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding

typealias Inflater<B> = (LayoutInflater, ViewGroup?, Boolean) -> B

abstract class BaseFragment<
        STATE : ViewState, INTENT : ViewIntent, ACTION : ViewAction,
        B : ViewBinding,
        VM : BaseViewModel<STATE, INTENT, ACTION>>
    (
    private val inflater: Inflater<B>,
    private val vmClass: Class<VM>
) : Fragment(), Render<STATE> {

    private var _binding: B? = null
    protected val binding by lazy(LazyThreadSafetyMode.NONE) {
        checkNotNull(_binding)
    }

    abstract fun factory(): ViewModelProvider.Factory
    private val viewModel: VM by lazy {
        ViewModelProvider(this, factory())[vmClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect(::render)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}