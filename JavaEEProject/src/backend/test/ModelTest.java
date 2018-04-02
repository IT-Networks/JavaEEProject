package backend.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import backend.enterpriseLogic.ModelHandler;
import backend.models.DepartureSchedulesModel;

public class ModelTest {

	@Test
	public void testGetAllDepartureSchedules() throws ParseException {
		ModelHandler mH = new ModelHandler();
		List<DepartureSchedulesModel> list = new ArrayList<DepartureSchedulesModel>();
		list = mH.getDepartureSchedulesModels("Tue Apr 17 17:46:00 CEST 2018");
		for (DepartureSchedulesModel dSM : list) {
			System.out.println(dSM.getFlugid() + " " + dSM.getStartort() + " " + dSM.getZielort() + " "
					+ dSM.getStatus() + " " + dSM.getAbflug() + " " + dSM.getAnkunft() + " " + dSM.getPreis());
		}
	}

}
