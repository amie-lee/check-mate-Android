package com.example.check_mate.src.main.mate

import android.os.Bundle
import android.view.View
import com.example.check_mate.R
import com.example.check_mate.config.BaseFragment
import com.example.check_mate.databinding.FragmentMateBinding
import kotlinx.android.synthetic.main.fragment_mate.*


class MateFragment : BaseFragment<FragmentMateBinding>(FragmentMateBinding::bind, R.layout.fragment_mate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    private fun showProfileDialog() {
        MateDialog(requireContext()) {

        }.show()
    }
}
