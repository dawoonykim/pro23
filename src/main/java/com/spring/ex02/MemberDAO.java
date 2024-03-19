package com.spring.ex02;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	// 연결이나 DataSource에서 sqlSession을 생성
	public static SqlSessionFactory sqlMapper = null;

	static SqlSessionFactory getInstance() {

		if (sqlMapper == null) {
			try {
				// DB 연동 설정 정보 파일 불러오기
				String resource = "mybatis/SqlMapConfig.xml";

				Reader reader = Resources.getResourceAsReader(resource);

				// SqlSessionFactory 객체 생성
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			} catch (Exception e) {
				System.out.println("sqlSessionFactory 객체 생성 중 예외 발생");
				/* e.printStackTrace(); */
			}

		}
		return sqlMapper;
	}

	// 회원 조회
	public List<MemberVO> selectAllMemberList() {

		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		// SqlSession
		// MyBatsi 작업을 위한 기본 Java 인터페이스입니다. 이 인터페이서를 통해 명령을 실행하고, 매퍼를 가져오고, 트랜잭션을 관리

		SqlSession session = sqlMapper.openSession();
		System.out.println("session 객체 : " + session);

		// sql문이 있는 곳에서 sql 실행 결과를 list로 받기
		List<MemberVO> memList = session.selectList("mapper.member.selectAllMemberList");
		return memList;
	}

	// 회원 ID로 이름 조회
	String selectName() {
		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		SqlSession session = sqlMapper.openSession();
		String name = session.selectOne("mapper.member.selectName");
		System.out.println("name 객체 : " + name);
		return name;

	}

	String selectPwd() {
		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		SqlSession session = sqlMapper.openSession();
		String pwd = session.selectOne("mapper.member.selectPwd");
		System.out.println("pwd 객체 : " + pwd);
		return pwd;
	}
	
	int selectPwd2() {
		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		SqlSession session = sqlMapper.openSession();
		int pwd = session.selectOne("mapper.member.selectPwd2");
		System.out.println("pwd 객체 : " + pwd);
		return pwd;
	}

}
