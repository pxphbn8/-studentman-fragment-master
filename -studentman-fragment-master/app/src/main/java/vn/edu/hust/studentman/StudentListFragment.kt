package vn.edu.hust.studentman

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class StudentListFragment : Fragment(R.layout.fragment_student_list) {

    private lateinit var listView: ListView
    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.list_view_students)

        val adapter = StudentAdapter(requireContext(), students)
        listView.adapter = adapter

        registerForContextMenu(listView)

        listView.setOnItemClickListener { _, _, position, _ ->
            val student = students[position]
            val bundle = Bundle().apply {
                putString("studentName", student.studentName)
                putString("studentId", student.studentId)
            }
            val fragment = EditStudentFragment().apply {
                arguments = bundle
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: android.view.MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add_new -> {
                // Mở Fragment AddStudentFragment
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AddStudentFragment())
                    .addToBackStack(null)
                    .commit()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val student = students[info.position]

        return when (item.itemId) {
            R.id.menu_edit -> {
                val bundle = Bundle().apply {
                    putString("studentName", student.studentName)
                    putString("studentId", student.studentId)
                }
                val fragment = EditStudentFragment().apply {
                    arguments = bundle
                }
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
                true
            }
            R.id.menu_remove -> {
                // Xóa sinh viên khỏi danh sách
                students.removeAt(info.position)
                (listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // Thiết lập Context Menu
    override fun onCreateContextMenu(menu: android.view.ContextMenu, v: View, menuInfo: android.view.ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }
}
