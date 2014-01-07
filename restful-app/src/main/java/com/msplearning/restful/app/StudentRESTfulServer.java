package com.msplearning.restful.app;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.msplearning.entity.Student;
import com.msplearning.restful.app.generic.GenericCrudRESTfulServer;
import com.msplearning.service.StudentService;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The StudentRESTfulServer class provides the RESTful services of entity
 * {@link Student}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/student")
public class StudentRESTfulServer extends GenericCrudRESTfulServer<Student, Long> {

	@Autowired
	private StudentService studentService;

	@Override
	protected GenericCrudService<Student, Long> getService() {
		return this.studentService;
	}

}
