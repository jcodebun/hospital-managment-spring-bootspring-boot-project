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

import in.co.hospital.mgt.dto.AllotRoomDTO;


@Repository
public class AllotRoomDAOImpl implements AllotRoomDAOInt {

	private static Logger log = Logger.getLogger(AllotRoomDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(AllotRoomDTO dto) {
		log.info("AllotRoomDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("AllotRoomDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(AllotRoomDTO dto) {
		log.info("AllotRoomDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("AllotRoomDAOImpl Delete method End");

	}

	@Override
	public AllotRoomDTO findBypk(long pk) {
		log.info("AllotRoomDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		AllotRoomDTO dto = (AllotRoomDTO) session.get(AllotRoomDTO.class, pk);
		log.info("AllotRoomDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public AllotRoomDTO findByName(String name) {
		log.info("AllotRoomDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AllotRoomDTO.class);
		criteria.add(Restrictions.eq("name",name));
		AllotRoomDTO dto = (AllotRoomDTO) criteria.uniqueResult();
		log.info("AllotRoomDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(AllotRoomDTO dto) {
		log.info("AllotRoomDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("AllotRoomDAOImpl update method End");
	}

	@Override
	public List<AllotRoomDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<AllotRoomDTO> list(int pageNo, int pageSize) {
		log.info("AllotRoomDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<AllotRoomDTO> query = session.createQuery("from AllotRoomDTO", AllotRoomDTO.class);
		List<AllotRoomDTO> list = query.getResultList();
		log.info("AllotRoomDAOImpl List method End");
		return list;
	}

	@Override
	public List<AllotRoomDTO> search(AllotRoomDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<AllotRoomDTO> search(AllotRoomDTO dto, int pageNo, int pageSize) {
		log.info("AllotRoomDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from AllotRoomDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			
			if (dto.getRoomId() > 0) {
				hql.append("and u.roomId = " + dto.getRoomId());
			}
		
		}
		Query<AllotRoomDTO> query = session.createQuery(hql.toString(), AllotRoomDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<AllotRoomDTO> list = query.getResultList();
		log.info("AllotRoomDAOImpl Search method End");
		return list;
	}

}
