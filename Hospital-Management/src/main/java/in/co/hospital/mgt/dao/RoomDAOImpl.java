package in.co.hospital.mgt.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.hospital.mgt.dto.RoomDTO;


@Repository
public class RoomDAOImpl implements RoomDAOInt {

	private static Logger log = Logger.getLogger(RoomDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(RoomDTO dto) {
		log.info("RoomDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("RoomDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(RoomDTO dto) {
		log.info("RoomDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("RoomDAOImpl Delete method End");

	}

	@Override
	public RoomDTO findBypk(long pk) {
		log.info("RoomDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		RoomDTO dto = (RoomDTO) session.get(RoomDTO.class, pk);
		log.info("RoomDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public RoomDTO findByRoomNo(String roomNo) {
		log.info("RoomDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(RoomDTO.class);
		criteria.add(Restrictions.eq("roomNo",roomNo));
		RoomDTO dto = (RoomDTO) criteria.uniqueResult();
		log.info("RoomDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(RoomDTO dto) {
		log.info("RoomDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("RoomDAOImpl update method End");
	}

	@Override
	public List<RoomDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<RoomDTO> list(int pageNo, int pageSize) {
		log.info("RoomDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<RoomDTO> query = session.createQuery("from RoomDTO", RoomDTO.class);
		List<RoomDTO> list = query.getResultList();
		log.info("RoomDAOImpl List method End");
		return list;
	}

	@Override
	public List<RoomDTO> search(RoomDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<RoomDTO> search(RoomDTO dto, int pageNo, int pageSize) {
		log.info("RoomDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from RoomDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			
			if (dto.getRoomNo() != null && dto.getRoomNo().length() > 0) {
				hql.append("and u.roomNo like '%" + dto.getRoomNo() + "%'");
			}
			
			
		
		}
		Query<RoomDTO> query = session.createQuery(hql.toString(), RoomDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<RoomDTO> list = query.getResultList();
		log.info("RoomDAOImpl Search method End");
		return list;
	}

}
