package Command;

import javax.swing.JOptionPane;

import src.AddRecordDialog;
import src.Employee;
import src.EmployeeDetails;

public class MenuCommands {
    private EmployeeDetails employeeDetails;

    public MenuCommands(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }
    
    //open
    public class OpenCommand implements Command {
        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.openFile();
            }
        }
    }
    //close 
    public class CloseCommand implements Command {
        public CloseCommand(EmployeeDetails employeeDetails) {	
		}
		@Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                new CloseCommand(employeeDetails).execute();
            }
        }
    }
    // add record
    public class AddRecordCommand implements Command {
        private EmployeeDetails employeeDetails;
        private AddRecordDialog addRecordDialog;

        public AddRecordCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }
        
        public AddRecordCommand(AddRecordDialog addRecordDialog) {
            this.addRecordDialog = addRecordDialog;
        }

        @Override
        public void execute() {
            Employee newEmployee = createNewEmployeeFromDialog();
            if (newEmployee != null) {
                employeeDetails.addRecord(newEmployee);
                employeeDetails.displayRecords(newEmployee);
                employeeDetails.changesMade = true;
            }
        }

        private Employee createNewEmployeeFromDialog() {
            if (addRecordDialog.checkInput()) {
                boolean fullTime = false;
                if (((String) addRecordDialog.fullTimeCombo.getSelectedItem()).equalsIgnoreCase("Yes")) {
                    fullTime = true;
                }
                return new Employee(
                        Integer.parseInt(addRecordDialog.idField.getText()),
                        addRecordDialog.ppsField.getText().toUpperCase(),
                        addRecordDialog.surnameField.getText().toUpperCase(),
                        addRecordDialog.firstNameField.getText().toUpperCase(),
                        addRecordDialog.genderCombo.getSelectedItem().toString().charAt(0),
                        addRecordDialog.departmentCombo.getSelectedItem().toString(),
                        Double.parseDouble(addRecordDialog.salaryField.getText()),
                        fullTime);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong values or format! Please check!");
                addRecordDialog.setToWhite();
                return null;
            }
        }
    }
    //cancel change
    public class CancelChangeCommand implements Command {
        private EmployeeDetails employeeDetails;
        
        public CancelChangeCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }
        
        public void execute() {
            employeeDetails.cancelChange();
        }
    }
    //delete record
    public class DeleteRecordCommand implements Command {
        private final EmployeeDetails employeeDetails;

        public DeleteRecordCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.deleteRecord();
            }
        }
    }
    //display all records
    public class DisplayAllCommand implements Command {
        private final EmployeeDetails employeeDetails;

        public DisplayAllCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                if (employeeDetails.isSomeoneToDisplay()) {
                    employeeDetails.displayEmployeeSummaryDialog();
                }
            }
        }
    }
    //edit details
    public class EditDetailsCommand implements Command {
        private final EmployeeDetails employeeDetails;

        public EditDetailsCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.editDetails();
            }
        }
    }
    //first record
    public class FirstRecordCommand implements Command {
        private EmployeeDetails employeeDetails;
        
        public FirstRecordCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }
        
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.firstRecord();
                employeeDetails.displayRecords(employeeDetails.getCurrentEmployee());
            }
        }
    }
    //last record
    public class LastRecordCommand implements Command {
        private EmployeeDetails employeeDetails;
        
        public LastRecordCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }
        
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.lastRecord();
                employeeDetails.displayRecords(employeeDetails.getCurrentEmployee());
            }
        }
    }
    //next record
    public class NextRecordCommand implements Command {
        private EmployeeDetails employeeDetails;
        
        public NextRecordCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }
        
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.nextRecord();
                employeeDetails.displayRecords(employeeDetails.getCurrentEmployee());
            }
        }
    }
    //previous record
    public class PreviousRecordCommand implements Command {
        private EmployeeDetails employeeDetails;
        
        public PreviousRecordCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }
        
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.previousRecord();
                employeeDetails.displayRecords(employeeDetails.getCurrentEmployee());
            }
        }
    }
    //save as
    public class SaveAsCommand implements Command {

        private EmployeeDetails employeeDetails;

        public SaveAsCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.saveFileAs();
            }
        }
    }
    //save
    public class SaveCommand implements Command {

        private EmployeeDetails employeeDetails;

        public SaveCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.saveFile();
            }
        }
    }
    //save record
    public class SaveRecordCommand implements Command {
        private EmployeeDetails employeeDetails;
        
        public SaveRecordCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }
        
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.saveFile();
                employeeDetails.change = false;
            }
        }
    }
    //search by id
    public class SearchByIdCommand implements Command {

        private EmployeeDetails employeeDetails;

        public SearchByIdCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.searchEmployeeById();
            }
        }
    }
    //search by id dialog
    public class SearchByIdDialogCommand implements Command {
        private EmployeeDetails employeeDetails;

        public SearchByIdDialogCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.displaySearchByIdDialog();
            }
        }
    }
    //search by surname
    public class SearchBySurnameCommand implements Command {

        private EmployeeDetails employeeDetails;

        public SearchBySurnameCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.searchEmployeeBySurname();
            }
        }
    }
    //search by surname dialog
    public class SearchBySurnameDialogCommand implements Command {
        private EmployeeDetails employeeDetails;

        public SearchBySurnameDialogCommand(EmployeeDetails employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        @Override
        public void execute() {
            if (employeeDetails.checkInput() && !employeeDetails.checkForChanges()) {
                employeeDetails.displaySearchBySurnameDialog();
            }
        }
    }
}
