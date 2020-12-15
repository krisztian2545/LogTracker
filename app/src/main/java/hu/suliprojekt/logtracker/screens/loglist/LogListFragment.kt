package hu.suliprojekt.logtracker.screens.loglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import hu.suliprojekt.logtracker.R
import hu.suliprojekt.logtracker.databinding.FragmentLogListBinding

class LogListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentLogListBinding>(inflater ,R.layout.fragment_log_list, container, false)

        val args = LogListFragmentArgs.fromBundle(requireArguments())
        binding.textView.text = args.appName

        return binding.root
    }

}