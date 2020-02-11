package com.example.squarerepos.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squarerepos.R.layout
import com.example.squarerepos.di.app.ViewModelFactory
import com.example.squarerepos.domain.UseCaseResult
import com.example.squarerepos.domain.repos.FetchReposResult
import com.example.squarerepos.domain.repos.RepositoryView
import com.example.squarerepos.ui.repos.adapter.ReposAdapter
import com.example.squarerepos.utils.ext.toggleVisibility
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.repos_screen.*
import javax.inject.Inject


class ReposFragment : DaggerFragment() {

    companion object {
        fun newInstance(): ReposFragment = ReposFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ReposViewModel
    private lateinit var reposAdapter: ReposAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(layout.repos_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(ReposViewModel::class.java)
        viewModel.reposFetchResult.observe(viewLifecycleOwner, Observer {
            onReposResult(it)
        })
    }

    private fun onReposResult(it: UseCaseResult<FetchReposResult>?) {
        when (it) {
            is UseCaseResult.Success -> onReposLoaded(it.data.repos)
            is UseCaseResult.Error -> onReposFetchError(it.toString())
        }
    }

    private fun onReposLoaded(it: List<RepositoryView>?) {
        error_container.visibility = View.GONE
        loader.visibility = View.GONE

        reposAdapter.submitList(it!!)
    }

    private fun onReposFetchError(error: String?) {
        error_container.visibility = View.VISIBLE
        loader.visibility = View.GONE

        retry.setOnClickListener { onRetryClick() }
    }

    private fun onRetryClick() {
        error_container.visibility = View.GONE
        loader.visibility = View.VISIBLE

        viewModel.fetchRepos()
    }

    fun toggleErrorState(enabled: Boolean) {
        error_container.toggleVisibility(!enabled)
        loader.toggleVisibility(enabled)
    }

    private fun initViews() {
        reposAdapter = ReposAdapter()

        repos.adapter = reposAdapter
        repos.layoutManager = LinearLayoutManager(context!!)
        repos.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}