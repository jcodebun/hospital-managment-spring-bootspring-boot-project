package in.co.hospital.mgt.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.hospital.mgt.dto.PatientSummeryDTO;


@Repository
public class PatientSummeryDAOImpl implements PatientSummeryDAOInt {

	private static Logger log = Logger.getLogger(PatientSummeryDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(PatientSummeryDTO dto) {
		log.info("PatientSummeryDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("PatientSummeryDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(PatientSummeryDTO dto) {
		log.info("PatientSummeryDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("PatientSummeryDAOImpl Delete method End");

	}

	@Override
	public PatientSummeryDTO findBypk(long pk) {
		log.info("PatientSummeryDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		PatientSummeryDTO dto = (PatientSummeryDTO) session.get(PatientSummeryDTO.class, pk);
		log.info("PatientSummeryDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public PatientSummeryDTO findByName(String name) {
		log.info("PatientSummeryDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(PatientSummeryDTO.class);
		criteria.add(Restrictions.eq("name",name));
		PatientSummeryDTO dto = (PatientSummeryDTO) criteria.uniqueResult();
		log.info("PatientSummeryDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(PatientSummeryDTO dto) {
		log.info("PatientSummeryDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("PatientSummeryDAOImpl update method End");
	}

	@Override
	public List<PatientSummeryDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<PatientSummeryDTO> list(int pageNo, int pageSize) {
		log.info("PatientSummeryDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<PatientSummeryDTO> query = session.createQuery("from PatientSummeryDTO", PatientSummeryDTO.class);
		List<PatientSummeryDTO> list = query.getResultList();
		log.info("PatientSummeryDAOImpl List method End");
		return list;
	}

	@Override
	public List<PatientSummeryDTO> search(PatientSummeryDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<PatientSummeryDTO> search(PatientSummeryDTO dto, int pageNo, int pageSize) {
		log.info("PatientSummeryDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from PatientSummeryDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			
			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}
		}
		Query<PatientSummeryDTO> query = session.createQuery(hql.toString(), PatientSummeryDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<PatientSummeryDTO> list = query.getResultList();
		log.info("PatientSummeryDAOImpl Search method End");
		return list;
	}
	
	@Override
	public Blob getFileById(long id) throws SerialException, SQLException {
		Session session = entityManager.unwrap(Session.class);
		PatientSummeryDTO	person = (PatientSummeryDTO) session.get(PatientSummeryDTO.class, id);
		byte[] blob = person.getReportFile();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

}
