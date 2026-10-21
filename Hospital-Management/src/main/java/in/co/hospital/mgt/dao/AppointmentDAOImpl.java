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

import in.co.hospital.mgt.dto.AppointmentDTO;


@Repository
public class AppointmentDAOImpl implements AppointmentDAOInt {

	private static Logger log = Logger.getLogger(AppointmentDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(AppointmentDTO dto) {
		log.info("AppointmentDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("AppointmentDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(AppointmentDTO dto) {
		log.info("AppointmentDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("AppointmentDAOImpl Delete method End");

	}

	@Override
	public AppointmentDTO findBypk(long pk) {
		log.info("AppointmentDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		AppointmentDTO dto = (AppointmentDTO) session.get(AppointmentDTO.class, pk);
		log.info("AppointmentDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public AppointmentDTO findByName(String name) {
		log.info("AppointmentDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AppointmentDTO.class);
		criteria.add(Restrictions.eq("name",name));
		AppointmentDTO dto = (AppointmentDTO) criteria.uniqueResult();
		log.info("AppointmentDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(AppointmentDTO dto) {
		log.info("AppointmentDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("AppointmentDAOImpl update method End");
	}

	@Override
	public List<AppointmentDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<AppointmentDTO> list(int pageNo, int pageSize) {
		log.info("AppointmentDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("from AppointmentDTO", AppointmentDTO.class);
		List<AppointmentDTO> list = query.getResultList();
		log.info("AppointmentDAOImpl List method End");
		return list;
	}

	@Override
	public List<AppointmentDTO> search(AppointmentDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<AppointmentDTO> search(AppointmentDTO dto, int pageNo, int pageSize) {
		log.info("AppointmentDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from AppointmentDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				hql.append("and u.firstName like '%" + dto.getFirstName() + "%'");
			}
			
			
		
		}
		Query<AppointmentDTO> query = session.createQuery(hql.toString(), AppointmentDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<AppointmentDTO> list = query.getResultList();
		log.info("AppointmentDAOImpl Search method End");
		return list;
	}

}
