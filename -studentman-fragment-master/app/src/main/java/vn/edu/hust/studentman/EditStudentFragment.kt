package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class EditStudentFragment : Fragment(R.layout.fragment_edit_student) {

    private lateinit var editTextName: EditText
    private lateinit var editTextId: EditText
    private lateinit var buttonSave: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextName = view.findViewById(R.id.edit_text_name)
        editTextId = view.findViewById(R.id.edit_text_id)
        buttonSave = view.findViewById(R.id.button_save)

        val studentName = arguments?.getString("studentName")
        val studentId = arguments?.getString("studentId")

        editTextName.setText(studentName)
        editTextId.setText(studentId)

        buttonSave.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
