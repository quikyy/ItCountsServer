package com.itcounts.service.interfaces;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.summary.SummaryDTO;
import java.sql.Date;

public interface ISummaryService {

	SummaryDTO getSummary(UserDAO userDao, Date startDate, Date endDate);
}
