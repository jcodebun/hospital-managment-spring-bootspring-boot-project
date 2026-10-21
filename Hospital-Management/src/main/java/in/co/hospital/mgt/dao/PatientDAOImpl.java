package in.co.hospital.mgt.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.hospital.mgt.dto.PatientDTO;


@Repository
public class PatientDAOImpl implements PatientDAOInt {

	private static Logger log = Logger.getLogger(PatientDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(PatientDTO dto) {
		log.info("PatientDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("PatientDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(PatientDTO dto) {
		log.info("PatientDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("PatientDAOImpl Delete method End");

	}

	@Override
	public PatientDTO findBypk(long pk) {
		log.info("PatientDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		PatientDTO dto = (PatientDTO) session.get(PatientDTO.class, pk);
		log.info("PatientDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public PatientDTO findByName(String name) {
		log.info("PatientDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(PatientDTO.class);
		criteria.add(Restrictions.eq("name",name));
		PatientDTO dto = (PatientDTO) criteria.uniqueResult();
		log.info("PatientDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(PatientDTO dto) {
		log.info("PatientDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("PatientDAOImpl update method End");
	}

	@Override
	public List<PatientDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<PatientDTO> list(int pageNo, int pageSize) {
		log.info("PatientDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<PatientDTO> query = session.createQuery("from PatientDTO", PatientDTO.class);
		List<PatientDTO> list = query.getResultList();
		log.info("PatientDAOImpl List method End");
		return list;
	}

	@Override
	public List<PatientDTO> search(PatientDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<PatientDTO> search(PatientDTO dto, int pageNo, int pageSize) {
		log.info("PatientDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from PatientDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				hql.append("and u.firstName like '%" + dto.getFirstName() + "%'");
			}
			
			if (dto.getEmailId() != null && dto.getEmailId().length() > 0) {
				hql.append("and u.emailId like '%" + dto.getEmailId() + "%'");
			}
			
		
		}
		Query<PatientDTO> query = session.createQuery(hql.toString(), PatientDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<PatientDTO> list = query.getResultList();
		log.info("PatientDAOImpl Search method End");
		return list;
	}

}
