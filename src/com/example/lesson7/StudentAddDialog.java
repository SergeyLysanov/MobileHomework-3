package com.example.lesson7;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.example.lesson7.StudentEditDialog.EditStudentDialogListener;

import database.Student;

@SuppressLint("ValidFragment")
public class StudentAddDialog extends StudentDialog
{
    public interface AddStudentDialogListener {
        public void onAddDialogPositiveClick(Student student);
    }
    
    private AddStudentDialogListener       mListener;
    
	public StudentAddDialog(Student student) {
		super(student);
	}
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (AddStudentDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement EditStudentDialogListener");
        }
    }
        
    @Override
    protected void clickPositive() {
    	mListener.onAddDialogPositiveClick(mStudent);
    }
}
