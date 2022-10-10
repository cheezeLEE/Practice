package com.practice.dao.mysql;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlDAO{
	
	@Autowired
	@Qualifier("mysqlSqlSession")
	private SqlSessionTemplate sqlSessionTemplate;

	public List<Map<String, Object>> getUserInfo2(int userSeq) {
		// userMapper 라는 부분과 5단계에 있는 mapper.xml 파일의 namespace를 동일하게 맞춰준다
        //.getUserInfo 와 5단계에 있는 <select id= 부분를 동일하게 맞춰준다.
        return sqlSessionTemplate.selectList("mysqlTestMapper.getUserInfo2",userSeq);
	}

}
