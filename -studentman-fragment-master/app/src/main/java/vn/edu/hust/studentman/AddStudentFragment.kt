package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class AddStudentFragment : Fragment(R.layout.fragment_add_student) {

    private lateinit var editTextName: EditText
    private lateinit var editTextId: EditText
    private lateinit var buttonAdd: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextName = view.findViewById(R.id.edit_text_name)
        editTextId = view.findViewById(R.id.edit_text_id)
        buttonAdd = view.findViewById(R.id.button_add)

        buttonAdd.setOnClickListener {
            val studentName = editTextName.text.toString()
            val studentId = editTextId.text.toString()

            
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
