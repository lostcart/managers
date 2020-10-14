package com.lost.managers.features.managers

import android.view.View
import androidx.lifecycle.Observer
import com.lost.domain.models.Manager
import com.lost.managers.R
import com.lost.popeat.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.managers.*
import javax.inject.Inject

/**
 * Fragment to show a list of managers and let the user filter them
 */
@AndroidEntryPoint
class ManagersFragment : BaseFragment() {
    @Inject
    lateinit var viewModel: ManagersViewModel

    override val layoutId: Int
        get() = R.layout.managers

    override fun setupViews() {
    }

    override fun setupViewModel() {
        viewModel.isLoading().observe(viewLifecycleOwner, Observer(::showLoading))
        viewModel.error().observe(viewLifecycleOwner, Observer(::showError))
        viewModel.managersList().observe(viewLifecycleOwner, Observer(::showManagers))
        viewModel.getManagers()
    }

    private fun showLoading(loading: Boolean) {
        managers_progressbar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun showError(throwable: Throwable) {
    }

    private fun showManagers(managers: List<Manager>) {

    }
}