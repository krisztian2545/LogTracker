package hu.suliprojekt.logtracker.screens.loglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import hu.suliprojekt.logtracker.R
import hu.suliprojekt.logtracker.database.LogDetailsDatabase
import hu.suliprojekt.logtracker.databinding.FragmentLogListBinding

class LogListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentLogListBinding>(inflater ,R.layout.fragment_log_list, container, false)

        val args = LogListFragmentArgs.fromBundle(requireArguments())


        val application = requireNotNull(this.activity).application
        val database = LogDetailsDatabase.getInstance(application).logDetailsDatabaseDao

        val viewModelFactory = LogListViewModelFactory(args.appName, database, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LogListViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.logListViewModel = viewModel

        val adapter = LogListItemAdapter()
        binding.logListRecyclerView.adapter = adapter
        binding.logListRecyclerView.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        viewModel.logList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

}