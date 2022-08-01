package com.example.lab4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.lab4.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val TAG ="firstFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgViewInterest.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.calculate.setOnClickListener{
            val priceEdit:EditText = binding.editNumberCarPrice
            val interestRateEdit:EditText = binding.editNumberRate
            val periodEdit:EditText = binding.editNumberPeriod
            val downPymtEdit:EditText = binding.editNumberDwnPymt
            val incomeEdit:EditText = binding.editNumberInc

            val price = priceEdit.text.toString().toFloat()
            val interestRate = interestRateEdit.text.toString().toString().toFloat()
            val period = periodEdit.text.toString().toFloat()
            val downPymt = downPymtEdit.text.toString().toFloat()
            val income = incomeEdit.text.toString().toFloat()

            var loanAmt = price - downPymt
            var interestCharge = loanAmt * interestRate / 100 * period
            var monthlyPymt = (loanAmt + interestCharge) / period / 12

            binding.loanAmt.text = String.format("%s%.2f",
                getString(R.string.laonAmt),loanAmt)
            binding.interest.text = String.format("%s%.2f",getString(R.string.interestAmt), interestCharge)
            binding.mthPymt.text = String.format("%s%.2f",getString(R.string.mthPayment), monthlyPymt)

            if(income*0.3 >= monthlyPymt){
                binding.eligible.text = String.format("%s",getString(R.string.eligible1))
            }else{
                binding.eligible.text = String.format("%s",getString(R.string.eligible2))
            }
        }
        binding.btnReset.setOnClickListener{
            binding.editNumberCarPrice.text = null;
            binding.editNumberRate.text = null
            binding.editNumberPeriod.text = null
            binding.editNumberDwnPymt.text = null
            binding.editNumberInc.text = null

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}