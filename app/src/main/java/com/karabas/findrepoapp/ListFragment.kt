package com.karabas.findrepoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.karabas.findrepoapp.databinding.ListFragmentBinding
import com.karabas.findrepoapp.model.Repository
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: ListViewModel by viewModel()

    private var binding: ListFragmentBinding? = null

    private val repoAdapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        initUI()
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getRepoLiveData().observe(viewLifecycleOwner, { onRepoDataChanged(it) })
    }

    private fun initUI() {
        binding?.run {
            repoListRecycler.layoutManager = LinearLayoutManager(requireContext())
            repoListRecycler.adapter = repoAdapter
            search.setOnClickListener { onSearchClick() }
        }
    }

    private fun onSearchClick() {
        val searchQuery = binding?.searchQuery?.text.toString()
        searchQuery.let { viewModel.startRepoSearch(it) }
    }

    private fun onRepoDataChanged(resource: Resource<List<Repository>>) {
        when (resource.status) {
            Status.SUCCESS -> {
                hideProgress()
                resource.data?.let { repoAdapter.updateRepos(it) }
            }

            Status.ERROR -> {
                hideProgress()
                resource.msg?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
            }

            Status.LOADING -> {
                repoAdapter.updateRepos(emptyList())
                showProgress()
            }
        }
    }

    private fun showProgress() {
        binding?.run { progressBar.visibility = View.VISIBLE }
    }

    private fun hideProgress() {
        binding?.run { progressBar.visibility = View.GONE }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}