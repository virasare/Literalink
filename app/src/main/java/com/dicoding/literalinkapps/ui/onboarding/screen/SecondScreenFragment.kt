package com.dicoding.literalinkapps.ui.onboarding.screen

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.literalinkapps.R
import com.dicoding.literalinkapps.ui.welcome.WelcomeActivity

class SecondScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val finish = view.findViewById<Button>(R.id.next)
        val back = view.findViewById<Button>(R.id.back)
        val skip = view.findViewById<View>(R.id.skip)

        finish.setOnClickListener {
            startActivity(Intent(requireContext(), WelcomeActivity::class.java))
            requireActivity().finish()
        }

        back.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 1
        }

        skip.setOnClickListener {
            val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("onboarding_pref", MODE_PRIVATE)
            sharedPref.edit().putBoolean("onboarding_completed", true).apply()
            val intent = Intent(activity, WelcomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}