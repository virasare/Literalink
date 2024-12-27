package com.dicoding.literalinkapps.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.literalinkapps.ui.onboarding.screen.FirstScreenFragment
import com.dicoding.literalinkapps.ui.onboarding.screen.SecondScreenFragment

class OnBoardingAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstScreenFragment()
            1 -> SecondScreenFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}