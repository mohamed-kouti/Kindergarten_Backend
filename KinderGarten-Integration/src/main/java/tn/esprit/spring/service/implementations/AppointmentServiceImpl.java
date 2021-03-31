package tn.esprit.spring.service.implementations;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Appointment;
import tn.esprit.spring.entity.EDaysOfWeek;
import tn.esprit.spring.entity.KinderGartenOpeningDay;
import tn.esprit.spring.entity.KinderGarten_owner;
import tn.esprit.spring.repository.IAppointmentRepository;
import tn.esprit.spring.repository.IKinderGartenOpeningDayRepository;
import tn.esprit.spring.repository.IKinderGarten_ownerRepository;
import tn.esprit.spring.service.interfaces.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	IAppointmentRepository iAppointmentRepository;
	@Autowired
	IKinderGartenOpeningDayRepository iKinderGartenOpeningDayRepository;
	@Autowired
	IKinderGarten_ownerRepository iKinderGarten_ownerRepository;

	Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	@Override
	public List<Object> setApointmentDate(Appointment appointment) {

		List<Object> resp = new ArrayList<Object>();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		logger.info("Start processing");
		Boolean inDays = false;
		List<String> takenHours = new ArrayList<String>();
		Appointment _appointmentData = null;
		Map<String, Object> msg = new HashMap<String, Object>();

		Optional<KinderGarten_owner> kinderGarten_owner = iKinderGarten_ownerRepository
				.findById(appointment.getGarten_owner().getId());
		List<KinderGartenOpeningDay> kinderGartenOpeningDays = iKinderGartenOpeningDayRepository
				.findByKinderGarten(kinderGarten_owner.get().getKindergarten());

		List<Appointment> appointments = iAppointmentRepository
				.findByGarten_ownerDateTime(appointment.getGarten_owner(), appointment.getDate_app());

		for (Appointment _appointment : appointments) {
			logger.info("Start time : " + _appointment.getStartTime());
			try {
				takenHours.add(dateFormat.format(_appointment.getStartTime()));
			} catch (Exception e) {
				logger.error("Exception " + e.getMessage());
			}
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(appointment.getDate_app());

		DateFormatSymbols symbols = new DateFormatSymbols(Locale.FRANCE);

		int day = calendar.get(Calendar.DAY_OF_WEEK);

		String startHour = null;
		Date endtHour = null;

		for (KinderGartenOpeningDay kinderGartenOpeningDay : kinderGartenOpeningDays) {
			logger.info("Day : " + symbols.getWeekdays()[day] + " - " + kinderGartenOpeningDay.getDay());
			if (kinderGartenOpeningDay.getDay() == EDaysOfWeek.valueOf(symbols.getWeekdays()[day])) {

				startHour = dateFormat.format(kinderGartenOpeningDay.getTimeOpen());
				endtHour = kinderGartenOpeningDay.getTimeClose();
				inDays = true;
			}

		}
		String toAssignDate = null;
		if (inDays) {
			String[] startHourSplit = startHour.split(":");

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHourSplit[0]));
			cal.set(Calendar.MINUTE, Integer.parseInt(startHourSplit[1]));
			cal.set(Calendar.SECOND, 0);
			while (!dateFormat.format(cal.getTime()).equals(dateFormat.format(endtHour))) {
				if (!takenHours.contains(dateFormat.format(cal.getTime()))) {
					toAssignDate = dateFormat.format(cal.getTime());
					System.out.println(toAssignDate);
					break;

				}
				cal.add(Calendar.MINUTE, 30);

			}

		}
		if (!inDays) {
			msg.put("message", "sorry , KinderGarten closed during this day");
			resp.add(msg);
		} else if (toAssignDate != null) {

			Date tt = null;
			try {
				tt = new SimpleDateFormat("HH:mm:ss").parse(toAssignDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int addMinuteTime = 30;
			Date targetTime = AppointmentServiceImpl.addMinutesToDate(addMinuteTime, tt); // add minute
			appointment.setStartTime(tt);
			appointment.setEndTime(targetTime);
			_appointmentData = iAppointmentRepository.save(appointment);
			msg.put("message", "Creared");
			resp.add(_appointmentData);
			resp.add(msg);
		} else {
			msg.put("message", "sorry , No date available in this day try another one");
			resp.add(msg);
		}

		logger.info("End processing");
		return resp;
	}

	private static Date addMinutesToDate(int minutes, Date beforeTime) {
		final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs

		long curTimeInMs = beforeTime.getTime();
		Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
		return afterAddingMins;
	}

}

