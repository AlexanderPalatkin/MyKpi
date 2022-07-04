package com.example.mykpi

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.mykpi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var startAct: Boolean = false

    //    save data
    private var counterVisits: Int = 0

    // save data
    private var counterFirstTask: Int = 0
    private var counterSecondTask: Int = 0
    private var counterThirdTask: Int = 0

    private var firstResult: Double = 0.0
    private var secondResult: Double = 0.0
    private var thirdResult: Double = 0.0

    // save data
    private var firstResultPercent: Int = 0
    private var secondResultPercent: Int = 0
    private var thirdResultPercent: Int = 0

    //    save data
    var saverFirstResultPercent = 0
    var saverSecondResultPercent = 0
    var saverThirdResultPercent = 0

    //        save data
    var totalPercent: Double = 0.0
    var saverTotalPercent: Double = 0.0
    var checkPercentShare = false

    lateinit var sPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        loadData()
        checkLoadData()
        startActOperation()
        calculateResultFirstKpi()
        calculateShare()
        saveResult()
    }

    private fun saveResult() {
        binding.bSave.setOnClickListener {
            if (!startAct) {
                Toast.makeText(this, "Добавьте визит", Toast.LENGTH_SHORT).show()
            } else if (!checkPercentShare) {
                Toast.makeText(this, "Заполните Долю полки", Toast.LENGTH_SHORT).show()
            } else {
                startAct = false
                checkPercentShare = false
                binding.tvAllVisits.setBackgroundResource(R.color.background_no_active)
                binding.tvTotalShare.setBackgroundResource(R.color.background_no_active)
                binding.etTotalVodka.isEnabled = false
                binding.etRustVodka.isEnabled = false
                binding.cbFirst.isChecked = false
                binding.cbSecond.isChecked = false
                binding.cbThird.isChecked = false
                binding.etTotalVodka.setText("")
                binding.etRustVodka.setText("")
                binding.tvCheckResultShare.text = "0.0"
            }

        }
    }

    private fun checkLoadData() {
        if (!startAct) {
            binding.etTotalVodka.isEnabled = false
            binding.etRustVodka.isEnabled = false
            binding.tvAllVisits.setBackgroundResource(R.color.background_no_active)
            binding.tvTotalShare.setBackgroundResource(R.color.background_no_active)
        } else {
            binding.etTotalVodka.isEnabled = true
            binding.etRustVodka.isEnabled = true
            binding.tvAllVisits.setBackgroundResource(R.color.background_active)
            binding.tvTotalShare.setBackgroundResource(R.color.background_active)
        }
    }

    private fun startActOperation() {
        binding.bAddVisit.setOnClickListener {
            if (!startAct) {
                counterVisits++
                binding.tvAllVisits.text = counterVisits.toString()
                startAct = true
                binding.tvAllVisits.setBackgroundResource(R.color.background_active)
                binding.etTotalVodka.isEnabled = true
                binding.etRustVodka.isEnabled = true

                firstResultPercent = saverFirstResultPercent
                var cof = counterVisits.toDouble() / 100
                firstResult = counterFirstTask.toDouble() / cof.toDouble()
                firstResultPercent = firstResult.toInt()
                binding.tvResultFirstBrand.text = "$firstResultPercent%"

                secondResultPercent = saverSecondResultPercent
                var cos = counterVisits.toDouble() / 100
                secondResult = counterSecondTask.toDouble() / cos.toDouble()
                secondResultPercent = secondResult.toInt()
                binding.tvResultSecondBrand.text = "$secondResultPercent%"

                thirdResultPercent = saverThirdResultPercent
                var con = counterVisits.toDouble() / 100
                thirdResult = counterThirdTask.toDouble() / con.toDouble()
                thirdResultPercent = thirdResult.toInt()
                binding.tvResultThirdBrand.text = "$thirdResultPercent%"
            }
        }
    }

    private fun calculateResultFirstKpi() {

        binding.cbFirst.setOnClickListener {
            if (startAct) {

                if (binding.cbFirst.isChecked) saverFirstResultPercent = firstResultPercent
                else firstResultPercent = saverFirstResultPercent

                if (binding.cbFirst.isChecked) {
                    counterFirstTask++
                    binding.tvAllFirst.text = counterFirstTask.toString()
                    var cof = counterVisits.toDouble() / 100
                    firstResult = counterFirstTask.toDouble() / cof.toDouble()
                    firstResultPercent = firstResult.toInt()
                    binding.tvResultFirstBrand.text = "$firstResultPercent%"
                } else {
                    counterFirstTask--
                    binding.tvAllFirst.text = counterFirstTask.toString()
                    binding.tvResultFirstBrand.text = "$firstResultPercent%"
                }
            } else {
                binding.cbFirst.isChecked = false
            }
        }

        binding.cbSecond.setOnClickListener {
            if (startAct) {
                if (binding.cbSecond.isChecked) saverSecondResultPercent = secondResultPercent
                else secondResultPercent = saverSecondResultPercent

                if (binding.cbSecond.isChecked) {
                    counterSecondTask++
                    binding.tvAllSecond.text = counterSecondTask.toString()
                    var cos = counterVisits.toDouble() / 100
                    secondResult = counterSecondTask.toDouble() / cos.toDouble()
                    secondResultPercent = secondResult.toInt()
                    binding.tvResultSecondBrand.text = "$secondResultPercent%"

                } else {
                    counterSecondTask--
                    binding.tvAllSecond.text = counterSecondTask.toString()
                    binding.tvResultSecondBrand.text = "$secondResultPercent%"
                }
            } else {
                binding.cbSecond.isChecked = false
            }

        }

        binding.cbThird.setOnClickListener {
            if (startAct) {
                if (binding.cbThird.isChecked) saverThirdResultPercent = thirdResultPercent
                else thirdResultPercent = saverThirdResultPercent

                if (binding.cbThird.isChecked) {
                    counterThirdTask++
                    binding.tvAllThird.text = counterThirdTask.toString()
                    var con = counterVisits.toDouble() / 100
                    thirdResult = counterThirdTask.toDouble() / con.toDouble()
                    thirdResultPercent = thirdResult.toInt()
                    binding.tvResultThirdBrand.text = "$thirdResultPercent%"
                } else {
                    counterThirdTask--
                    binding.tvAllThird.text = counterThirdTask.toString()
                    binding.tvResultThirdBrand.text = "$thirdResultPercent%"
                }
            } else {
                binding.cbThird.isChecked = false
            }
        }
    }

    private fun calculateShare() {
        binding.bCheckResultShare.setOnClickListener {
            if (binding.etTotalVodka.text.toString().isNotEmpty() &&
                binding.etTotalVodka.text.toString() != "0" &&
                binding.etRustVodka.text.toString().isNotEmpty()
            ) {
                if (startAct) {
                    if (!checkPercentShare) {
                        saverTotalPercent = totalPercent
                    } else {
                        totalPercent = saverTotalPercent
                    }
                    val totalVodka = binding.etTotalVodka.text.toString().toDouble()
                    val rustVodka = binding.etRustVodka.text.toString().toDouble()
                    val checkResultShop: Double = (totalVodka / 100).toDouble()
                    val resultShop: Double = rustVodka / checkResultShop

                    binding.tvCheckResultShare.text = resultShop.toString()

                    totalPercent += resultShop

                    var resultTotalShare = totalPercent / counterVisits

                    binding.tvTotalShare.text = resultTotalShare.toString()
                        .removeRange(5..resultTotalShare.toString().lastIndex)
                    checkPercentShare = true
                    binding.tvTotalShare.setBackgroundResource(R.color.background_active)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    fun clearAllFieldsAndStats() {
        startAct = false

        binding.etTotalVodka.isEnabled = false
        binding.etRustVodka.isEnabled = false
        binding.tvAllVisits.setBackgroundResource(R.color.background_no_active)
        binding.tvTotalShare.setBackgroundResource(R.color.background_no_active)

        counterVisits = 0
        counterFirstTask = 0
        counterSecondTask = 0
        counterThirdTask = 0

        firstResult = 0.0
        secondResult = 0.0
        thirdResult = 0.0

        firstResultPercent = 0
        secondResultPercent = 0
        thirdResultPercent = 0

        saverFirstResultPercent = 0
        saverSecondResultPercent = 0
        saverThirdResultPercent = 0

        totalPercent = 0.0
        saverTotalPercent = 0.0
        checkPercentShare = false

        binding.tvAllVisits.text = counterVisits.toString()

        binding.tvAllFirst.text = counterFirstTask.toString()
        binding.tvAllSecond.text = counterSecondTask.toString()
        binding.tvAllThird.text = counterThirdTask.toString()

        binding.tvResultFirstBrand.text = firstResultPercent.toString()
        binding.tvResultSecondBrand.text = secondResultPercent.toString()
        binding.tvResultThirdBrand.text = thirdResultPercent.toString()

        binding.cbFirst.isChecked = false
        binding.cbSecond.isChecked = false
        binding.cbThird.isChecked = false

//            create variables
        binding.tvTotalShare.text = "0.0"
        binding.tvCheckResultShare.text = "0.0"

        binding.etTotalVodka.setText("")
        binding.etRustVodka.setText("")
    }

    fun saveData() {
        sPref = getPreferences(MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sPref.edit()
        ed.putInt(Constance.COUNTER_VISITS, counterVisits)

        ed.putInt(Constance.COUNTER_FIRST_TASK, counterFirstTask)
        ed.putInt(Constance.COUNTER_SECOND_TASK, counterSecondTask)
        ed.putInt(Constance.COUNTER_THIRD_TASK, counterThirdTask)

        ed.putInt(Constance.FIRST_RESULT_PERCENT, firstResultPercent)
        ed.putInt(Constance.SECOND_RESULT_PERCENT, secondResultPercent)
        ed.putInt(Constance.THIRD_RESULT_PERCENT, thirdResultPercent)

        ed.putInt(Constance.SAVER_FIRST_RESULT_PERCENT, saverFirstResultPercent)
        ed.putInt(Constance.SAVER_SECOND_RESULT_PERCENT, saverSecondResultPercent)
        ed.putInt(Constance.SAVER_THIRD_RESULT_PERCENT, saverThirdResultPercent)

        ed.putFloat(Constance.TOTAL_PERCENT, totalPercent.toFloat())
        ed.putFloat(Constance.SAVER_TOTAL_PERCENT, saverTotalPercent.toFloat())

        ed.putBoolean(Constance.CHECK_PERCENT_SHARE, checkPercentShare)
        ed.putBoolean(Constance.START_ACT, startAct)
        ed.putBoolean(Constance.CHECK_BOX_FIRST, binding.cbFirst.isChecked)
        ed.putBoolean(Constance.CHECK_BOX_SECOND, binding.cbSecond.isChecked)
        ed.putBoolean(Constance.CHECK_BOX_THIRD, binding.cbThird.isChecked)

        ed.putString(Constance.RESULT_SHOP, binding.tvCheckResultShare.text.toString())
        ed.putString(Constance.TOTAL_VODKA, binding.etTotalVodka.text.toString())
        ed.putString(Constance.RUST_VODKA, binding.etRustVodka.text.toString())
        // todo total share
        ed.putString(Constance.TOTAL_SHARE, binding.tvTotalShare.text.toString())
//        ed.putString(Constance.CHANGE_TOTAL_SHARE, binding.tvChangeTotalShare.text.toString())
        ed.commit()
    }

    fun loadData() {
        sPref = getPreferences(MODE_PRIVATE)
        counterVisits = sPref.getInt(Constance.COUNTER_VISITS, 0)
        binding.tvAllVisits.text = counterVisits.toString()

        counterFirstTask = sPref.getInt(Constance.COUNTER_FIRST_TASK, 0)
        binding.tvAllFirst.text = counterFirstTask.toString()
        counterSecondTask = sPref.getInt(Constance.COUNTER_SECOND_TASK, 0)
        binding.tvAllSecond.text = counterSecondTask.toString()
        counterThirdTask = sPref.getInt(Constance.COUNTER_THIRD_TASK, 0)
        binding.tvAllThird.text = counterThirdTask.toString()

        firstResultPercent = sPref.getInt(Constance.FIRST_RESULT_PERCENT, 0)
        binding.tvResultFirstBrand.text = firstResultPercent.toString()
        secondResultPercent = sPref.getInt(Constance.SECOND_RESULT_PERCENT, 0)
        binding.tvResultSecondBrand.text = secondResultPercent.toString()
        thirdResultPercent = sPref.getInt(Constance.THIRD_RESULT_PERCENT, 0)
        binding.tvResultThirdBrand.text = thirdResultPercent.toString()

        saverFirstResultPercent = sPref.getInt(Constance.SAVER_FIRST_RESULT_PERCENT, 0)
        saverSecondResultPercent = sPref.getInt(Constance.SAVER_SECOND_RESULT_PERCENT, 0)
        saverThirdResultPercent = sPref.getInt(Constance.SAVER_THIRD_RESULT_PERCENT, 0)

        totalPercent = sPref.getFloat(Constance.TOTAL_PERCENT, 0.0f).toDouble()
        saverTotalPercent = sPref.getFloat(Constance.SAVER_TOTAL_PERCENT, 0.0f).toDouble()

        checkPercentShare = sPref.getBoolean(Constance.CHECK_PERCENT_SHARE, false)
        startAct = sPref.getBoolean(Constance.START_ACT, false)
        binding.cbFirst.isChecked = sPref.getBoolean(Constance.CHECK_BOX_FIRST, false)
        binding.cbSecond.isChecked = sPref.getBoolean(Constance.CHECK_BOX_SECOND, false)
        binding.cbThird.isChecked = sPref.getBoolean(Constance.CHECK_BOX_THIRD, false)

        binding.tvTotalShare.text = sPref.getString(Constance.TOTAL_SHARE, "0.0")
//        binding.tvChangeTotalShare.text = sPref.getString(Constance.CHANGE_TOTAL_SHARE, "")
        binding.tvCheckResultShare.text = sPref.getString(Constance.RESULT_SHOP, "0.0")

        val takeTotalVodka = sPref.getString(Constance.TOTAL_VODKA, "")
        binding.etTotalVodka.setText(takeTotalVodka)
        val takeRustVodka = sPref.getString(Constance.RUST_VODKA, "")
        binding.etRustVodka.setText(takeRustVodka)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.data_clear) {
            clearAllFieldsAndStats()
        }
        return true
    }

}