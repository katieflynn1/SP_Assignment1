package src;

import java.util.List;

public interface SearchDialog {
 
    List<Employee> search();

	void cancel();

	String getSearchText();
}
