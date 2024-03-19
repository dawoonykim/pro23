package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

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

	public List<Map<String, String>> selectAllMemberList() {

		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		// SqlSession
		// MyBatsi 작업을 위한 기본 Java 인터페이스입니다. 이 인터페이서를 통해 명령을 실행하고, 매퍼를 가져오고, 트랜잭션을 관리

		SqlSession session = sqlMapper.openSession();
		System.out.println("session 객체 : " + session);

		// sql문이 있는 곳에서 sql 실행 결과를 list로 받기
//		List<MemberVO> memList = session.selectList("mapper.member.selectAllMemberList");
		List<Map<String, String>> memList = session.selectList("mapper.member.selectAllMemberList");
		return memList;
	}

	// 회원 추가
	public void insertMember(com.spring.ex04.MemberVO memberVO) {

		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		SqlSession session = sqlMapper.openSession();
		System.out.println("session 객체 : " + session);

		session.insert("mapper.member.insertMember", memberVO);

		session.commit();
	}

	// 회원 삭제
	public void deleteMember(String id) {
		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		SqlSession session = sqlMapper.openSession();
		System.out.println("session 객체 : " + session);
		System.out.println(id);
		session.delete("mapper.member.deleteMember", id);

		session.commit();
		session.close();
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

	// 회원 ID로 pwd 조회
	String selectPwd() {
		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		SqlSession session = sqlMapper.openSession();
		String pwd = session.selectOne("mapper.member.selectPwd");
		System.out.println("pwd 객체 : " + pwd);
		return pwd;
	}

	// 회원 ID로 pwd 조회
	int selectPwd2() {
		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		SqlSession session = sqlMapper.openSession();
		int pwd = session.selectOne("mapper.member.selectPwd2");
		System.out.println("pwd 객체 : " + pwd);
		return pwd;
	}

	// 회원 ID로 회원이 있는지 여부 확인
	public MemberVO selectMemberByID(String id) {
		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		SqlSession session = sqlMapper.openSession();
		MemberVO member = session.selectOne("mapper.member.selectMemberByID", id);
		return member;
	}

	// 동적 SQL문을 이용한 조회
	// 리턴값 List<MemberVO>의 매개변수 MemberVO
	public List<Map<String, String>> searchMember(com.spring.ex04.MemberVO memberVO) {
		System.out.println(memberVO.getName() + "," + memberVO.getEmail());

		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		SqlSession session = sqlMapper.openSession();
		System.out.println("session 객체 : " + session);

		List<Map<String, String>> memList = session.selectList("mapper.member.searchMember", memberVO);
		session.commit();
		return memList;

	}

	
	// 동적 SQL문 (for each)를 이용한 여러개의 값 조회
	
	List<Map<String, String>> foreachSelect(List<String> list){
		sqlMapper = getInstance();
		System.out.println("sqlMapper 객체 : " + sqlMapper);
		// SqlSession
		// MyBatsi 작업을 위한 기본 Java 인터페이스입니다. 이 인터페이서를 통해 명령을 실행하고, 매퍼를 가져오고, 트랜잭션을 관리

		SqlSession session = sqlMapper.openSession();
		System.out.println("session 객체 : " + session);
		
		List<Map<String, String>> memList=session.selectList("mapper.member.foreachSelect",list);
		
		
		return memList;
	}
	
	
}
