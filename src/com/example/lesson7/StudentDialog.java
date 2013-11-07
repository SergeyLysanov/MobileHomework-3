package com.example.lesson7;

import database.Student;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

@SuppressLint("ValidFragment")
public class StudentDialog extends DialogFragment
{
    public interface EditStudentDialogListener {
        public void onDialogPositiveClick(Student student);
    }
    
    private EditText                   		mNameEditText;
    private EditText                   		mSurnameEditText;
    private EditText                   		mGroupEditText;
    
    private EditStudentDialogListener       mListener;
    private final Student 					mStudent;
    
	public StudentDialog(Student student) {
		this.mStudent = student;
	}
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (EditStudentDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement EditStudentDialogListener");
        }
    }
        
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        final View view = inflater.inflate(R.layout.student_dialog, null);
        builder.setView(view);
        
        mNameEditText   = (EditText)view.findViewById(R.id.studentName);
        mSurnameEditText   = (EditText)view.findViewById(R.id.studentSurname);
        mGroupEditText   = (EditText)view.findViewById(R.id.studentGroup);
        
        mNameEditText.setText(mStudent.getName());
        mSurnameEditText.setText(mStudent.getSurname());
        mGroupEditText.setText(mStudent.getGroupId().toString());
        
        builder.setMessage("Dialog")
               .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   updateStudent();		
                	   mListener.onDialogPositiveClick(mStudent);
                   }
               })
               .setNegativeButton("Cancel", null);
        
        return builder.create();
    }
    
    private void updateStudent()
    {
    	mStudent.setName(mNameEditText.getText().toString());
    	mStudent.setSurname(mSurnameEditText.getText().toString());
    	mStudent.setGroupId(Integer.parseInt(mGroupEditText.getText().toString()));
    }
}