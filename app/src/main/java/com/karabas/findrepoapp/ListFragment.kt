package com.karabas.findrepoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.karabas.findrepoapp.databinding.ListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: ListViewModel by viewModel()

    private var binding: ListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        initUI()
        return binding!!.root
    }

    private fun initUI() {
        binding?.run {
            repoListRecycler.layoutManager = LinearLayoutManager(requireContext())
            search.setOnClickListener { onSearchClick() }
        }
    }

    private fun onSearchClick() {
        val searchQuery = binding?.searchQuery?.text.toString()
        searchQuery.let { viewModel.startRepoSearch(it) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}