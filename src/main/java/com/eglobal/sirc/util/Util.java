package com.eglobal.sirc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Component;

import com.eglobal.sirc.model_local.Params;

@Component
public class Util {
	public static Params params = new Params();
	private static PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
	private static final Logger log = LogManager.getLogger(Util.class);
	private static final String FORMAT_DATE = "yyyy-MM-dd";
	private static String FeInicio=null;
	private static  String FeFin=null;
	private static  String IdAdquiriente=null;
	/**
	 * Retorna verdadero si las validaciones son correctas, caso contrario retona
	 * falso.
	 * 
	 * @param args
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean validateParams(String[] args) {
		boolean flag = false;
		int cont=0;
	    for (int i = 0; i < args.length; i++) {
	    	log.info("Argument " + i + ": " + args[i].toString());
	    	cont=i;
	        }
	    	log.info("valida args. " + cont);
		if (cont>=1) {
       	 if (cont==1 ) {
     		FeInicio = StringEscapeUtils.unescapeHtml4(policy.sanitize(args[0])); 
     		IdAdquiriente = StringEscapeUtils.unescapeHtml4(policy.sanitize(args[1])); 
     		
       		 if (FormattedDateMatcher.matches(FeInicio)) {

    				log.info("Se valida la fecha de inicio: " + true);
    				if (FormattedDateMatcher.matches(FeInicio)) {
    					log.info("Se valida la fecha de fin: " + true);
    					params.setFeInicio(FeInicio);
    					params.setFeFin(FeInicio);
    					params.setVentana(Integer.parseInt(IdAdquiriente));
    					flag = true;
    				} else {
	     				log.info("[{}]", ErrorCodes.cod04);
    					log.info("Fecha de fin incorrecta. " + false);
    					log.info("Formato correcto [{}]", FORMAT_DATE);
    					return false;
    				}
    			} else {
     				log.info("[{}]", ErrorCodes.cod04);
    				log.info("Fecha de inicio incorrecta. " + false);
    				log.info("Formato correcto [{}]", FORMAT_DATE);
    				return false;
    			}
       		 /* Se valida el orden de las fechas */
    			if (flag && validateOrderOfDates(FeInicio, FeInicio)) {
    				flag = true;
    			} else {
    				return false;
    			}

    			/* Se valida que el rango de días no exceda los días */
    			Integer maxDias = 60;
    			if (flag && numDaysBetweenDates(FeInicio, FeInicio) <= maxDias) {
    				flag = true;
    			} else {
    				log.info("El numero de dias exceden el rango permitido. " + false);
    				return false;
    			}
    			
    				log.info("Se valida la ventana de de ejecucion: " + true);
    				log.info("ventana de de ejecucion: " + IdAdquiriente);

    				if (Integer.parseInt(IdAdquiriente)>0) {
    					flag = true;
    				}else {
    				log.info("ingreso de ventana incorrecto oh no se encontro parecido : " + false);
    				flag = false;
    				}
    			
       	 }else if (cont==2) {
       		FeInicio = StringEscapeUtils.unescapeHtml4(policy.sanitize(args[0])); 
   			FeFin = StringEscapeUtils.unescapeHtml4(policy.sanitize(args[1])); 
     		IdAdquiriente = StringEscapeUtils.unescapeHtml4(policy.sanitize(args[2])); 
       		 if (FormattedDateMatcher.matches(FeInicio)) {
       			
	     				log.info("Se valida la fecha de inicio: " + true);
	     				if (FormattedDateMatcher.matches(FeFin)) {
	     					log.info("Se valida la fecha de fin: " + true);
	     					params.setFeInicio(FeInicio);
	     					params.setFeFin(FeFin);
	     					params.setVentana(Integer.parseInt(IdAdquiriente));
	     					flag = true;
	     				} else {
		     				log.info("[{}]", ErrorCodes.cod04);
	     					log.info("Fecha de fin incorrecta. " + false);
	     					log.info("Formato correcto [{}]", FORMAT_DATE);
	     					return false;
	     				}
	     			} else {
	     				log.info("[{}]", ErrorCodes.cod04);
	     				log.info("Fecha de inicio incorrecta. " + false);
	     				log.info("Formato correcto [{}]", FORMAT_DATE);
	     				return false;
	     			}
       		 /* Se valida el orden de las fechas */
    			if (flag && validateOrderOfDates(FeInicio, FeFin)) {
    				flag = true;
    			} else {
    				return false;
    			}

    			/* Se valida que el rango de días no exceda los días */
    			Integer maxDias = 60;
    			if (flag && numDaysBetweenDates(FeInicio, FeFin) <= maxDias) {
    				flag = true;
    			} else {
    				log.info("El numero de dias exceden el rango permitido. " + false);
    				return false;
    			}
    			
    				log.info("Se valida la ventana de de ejecucion: " + true);
    				log.info("ventana de de ejecucion: " + IdAdquiriente);
    				if (Integer.parseInt(IdAdquiriente)>0 ) {
    					flag = true;
    				}else {
    				log.info("ingreso de ventana incorrecto oh no se encontro parecido : " + false);
    				flag = false;
    				}
    			
			}
      
       } else {
			flag = false;
       }
	    

	        return flag;
	    }

	/**
	 * Obtiene el día del mes de la fecha que se recibe
	 * 
	 * @param cal
	 * @return
	 */
	public static void getNumberFile(String strDay, String strMes) {
		Integer day = Integer.parseInt(strDay);
		Integer mes = Integer.parseInt(strDay);

		/*
		 * El rango para ejecutar el 1er. periodo el dia debe estar entre [16 - ultimo
		 * día del mes]
		 */
		if (day >= 16 && day <= getLastDayOfMonth(mes)) {
//			OdsReportePbApplication.params.setNumberFile("_1");
		}
		/* El rango para ejecutar el 2do. periodo el dia debe estar entre [01 - 15] */
		if (day > 0 && day <= 15) {
//			OdsReportePbApplication.params.setNumberFile("_2");
		}
	}

	/**
	 * Agrega la fecha de inicio y fin para la ejecucíón del proceso batch
	 * 
	 * @return
	 */
	public static String[] fillArgs() {
		log.info("No se recibieron fecha de inicio y fecha de fin");
		log.info("Se toman fechas del sistema");
		String[] args = { "", "" };
		Calendar cal = Calendar.getInstance();
		String strMes = "";
		int mes = 0;
		int year = 0;
		int day = 0;

		/* Tomamos el día de consulta */
		day = Integer.parseInt(getDayOfDate(cal));
		mes = Integer.parseInt(getMonthOfDate(cal));
		year = Integer.parseInt(getYearOfDate(cal));

		strMes = mes < 10 ? "0" + mes : "" + mes;

		/* Si el día de consulta es mayor a 15 se trata de la 2da quincena */
		if (day >= 16 && day <= getLastDayOfMonth(mes)) {
			log.info("1er. periodo del mes");
			/* Fecha de inicio */
			args[1] = year + "-" + strMes + "-" + "01";

			/* Fecha de fin */
			args[2] = year + "-" + strMes + "-" + "15";
		}
		if (day > 0 && day <= 15) {
			log.info("2do. periodo del mes");

			/* Fecha de inicio */
			args[1] = year + "-" + strMes + "-" + "16";

			/* Fecha de fin */
			args[2] = year + "-" + strMes + "-" + getLastDayOfMonth(mes);
		}

		log.info("Fecha de inicio: [{}]", args[1]);
		log.info("Fecha de fin: [{}]", args[2]);
		return args;
	}

	/**
	 * Retorna el ultimo día del mes
	 * 
	 * @param mes
	 * @return
	 */
	public static int getLastDayOfMonth(int mes) {
		if (mes == 2) {
			return 29;
		} else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			return 30;
		} else {
			return 31;
		}
	}

	/**
	 * Retorna la fecha del día en tipo date, tomando el formato que se recibe
	 * 
	 * @param fechaInicio
	 * @return
	 */
	public static Date toDateFromFormatString(String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(sdf.format(new Date()));
	}

	/**
	 * Obtiene el ultimo día del mes
	 * 
	 * @param strFechaSp
	 * @return
	 */
	public static int getMaximunDayOfMonth(String strFechaSp) {
		if (strFechaSp != null) {
			Calendar cLastdayMonth = Calendar.getInstance();
			Date dMesInicio = getStringToDate(strFechaSp);
			cLastdayMonth.setTime(dMesInicio);
			cLastdayMonth.set(Calendar.DATE, cLastdayMonth.getActualMaximum(Calendar.DATE));
			return cLastdayMonth.get(Calendar.DATE);
		} else {
			log.info("[{}]", ErrorCodes.cod01);
			log.error("La fecha llego vacia. ");
			return 0;
		}
	}

	/**
	 * Convierte una fecha que llega como string a tipo Date
	 * 
	 * @param cadena
	 * @return
	 */
	public static Date getStringToDate(String cadena) {
		try {
			String str_date = cadena;
			DateFormat formatter;
			Date date;
			formatter = new SimpleDateFormat(FORMAT_DATE);
			date = (Date) formatter.parse(str_date);
			return date;
		} catch (ParseException e) {
			log.info("[{}]", ErrorCodes.cod04);
			log.error("Error durante la conversion", e);
			return null;
		}
	}

	/**
	 * Valida que la fecha ingresada tenga el formato [yyyy-MM-dd]
	 * 
	 * @param strDate
	 * @return
	 */
	public static boolean validateDate(String strDate) {
		int iDay = 0;
		int iMonth = 0;
		int iYear = 0;
		log.info("Se validara la fecha: [{}]", strDate);
		String[] arrDate = strDate != null ? strDate.split("-") : null;
		boolean flagDate = false;
		if (arrDate != null && arrDate.length > 2) {
			String year = arrDate[0];
			String month = arrDate[1];
			String day = arrDate[2];

			/* Validamos el año */
			if (year.matches("^[0-9]{4,4}$")) {
				iYear = Integer.parseInt(year);
				System.out.println("iYear: "+iYear);
				flagDate = iYear >= 2022 && iYear <= 2022;
			}
			if (!flagDate) {
				log.info("[{}]", ErrorCodes.cod04);
				log.info("El anio ingresado no es correcto");
				return false;
			}

			/* Validamos el mes */
			if (month.matches("^[0-9]{2,2}$") && flagDate) {
				iMonth = Integer.parseInt(month);
				System.out.println("iMonth: "+iMonth);
				flagDate = iMonth > 0 && iMonth < 13;
				if (!flagDate) {
					log.info("[{}]", ErrorCodes.cod04);
					log.info("El mes ingresado no es correcto [01 - 12]");
					return false;
				}
			} else {
				log.info("[{}]", ErrorCodes.cod04);
				log.info("El mes ingresado no es correcto [01 - 12]");
				return false;
			}

			/* Validamos el dia */
			if (day.matches("^[0-9]{2,2}$") && flagDate) {
				iDay = Integer.parseInt(day);
				System.out.println("iDay: "+iDay);

				if (month.equals("02")) {
					flagDate = iDay > 0 && iDay <= 29;
				} else if (month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {
					flagDate = iDay > 0 && iDay <= 30;
				} else {
					flagDate = iDay > 0 && iDay <= 31;
				}
				if (!flagDate) {
					log.info("[{}]", ErrorCodes.cod04);
					log.info("El dia ingresado no es correcto.");
					return false;
				}
			} else {
				log.info("[{}]", ErrorCodes.cod04);
				log.info("El dia ingresado no es correcto.");
				return false;
			}
			log.info("La fecha recibida es valida.");
		}
		return flagDate;
	}

	/**
	 * Valida que el orden las fechas ingresadas con el formato [yyyy-MM-dd] sea el
	 * correcto. <br/>
	 * [Fecha inicio] [fecha Fin]
	 * 
	 * @param feInicio
	 * @param feFin
	 * @return
	 */
	public static boolean validateOrderOfDates(String feInicio, String feFin) {
		log.info("Se validara el orden de las: [{}] [{}]", feInicio, feFin);
		Date dInicio = getStringToDate(feInicio);
		Date dFin = getStringToDate(feFin);
		if (dInicio.after(dFin)) {
			log.info("[{}]", ErrorCodes.cod04);
			log.info("El orden de las fechas no es correcto: [fecha_inicio] [fecha_fin]");
			return false;
		}
		log.info("El orden de las fechas es correcto.");
		return true;
	}

	/**
	 * Cambia un tipo Date a LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate dateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * Retorna el número de días entre las fechas recibidas como parámetros
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Integer numDaysBetweenDates(String fi, String fn) {
		LocalDate dtFi = LocalDate.parse(fi);
		LocalDate dtFn = LocalDate.parse(fn);
		long days = ChronoUnit.DAYS.between(dtFi, dtFn);
		log.info("Numero de dias: [{}] ", (days));
		return (int) days;
	}

	/**
	 * Obtiene el día del mes de la fecha que se recibe
	 * 
	 * @param cal
	 * @return
	 */
	public static String getDayOfDate(Calendar cal) {
		return new SimpleDateFormat("dd").format(cal.getTime());
	}

	/**
	 * Obtiene el número del mes de la fecha que se recibe
	 * 
	 * @param cal
	 * @return
	 */
	public static String getMonthOfDate(Calendar cal) {
		return new SimpleDateFormat("MM").format(cal.getTime());
	}

	/**
	 * Obtiene el número del mes de la fecha que se recibe
	 * 
	 * @param cal
	 * @return
	 */
	public static String getYearOfDate(Calendar cal) {
		return new SimpleDateFormat("yyyy").format(cal.getTime());
	}
}