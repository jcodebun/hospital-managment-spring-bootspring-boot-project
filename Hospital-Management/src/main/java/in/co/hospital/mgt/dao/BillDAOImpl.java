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

import in.co.hospital.mgt.dto.BillDTO;


@Repository
public class BillDAOImpl implements BillDAOInt {

	private static Logger log = Logger.getLogger(BillDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(BillDTO dto) {
		log.info("BillDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("BillDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(BillDTO dto) {
		log.info("BillDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("BillDAOImpl Delete method End");

	}

	@Override
	public BillDTO findBypk(long pk) {
		log.info("BillDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		BillDTO dto = (BillDTO) session.get(BillDTO.class, pk);
		log.info("BillDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public BillDTO findByName(String name) {
		log.info("BillDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(BillDTO.class);
		criteria.add(Restrictions.eq("name",name));
		BillDTO dto = (BillDTO) criteria.uniqueResult();
		log.info("BillDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(BillDTO dto) {
		log.info("BillDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("BillDAOImpl update method End");
	}

	@Override
	public List<BillDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<BillDTO> list(int pageNo, int pageSize) {
		log.info("BillDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<BillDTO> query = session.createQuery("from BillDTO", BillDTO.class);
		List<BillDTO> list = query.getResultList();
		log.info("BillDAOImpl List method End");
		return list;
	}

	@Override
	public List<BillDTO> search(BillDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<BillDTO> search(BillDTO dto, int pageNo, int pageSize) {
		log.info("BillDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from BillDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			
			
			
			
		
		}
		Query<BillDTO> query = session.createQuery(hql.toString(), BillDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<BillDTO> list = query.getResultList();
		log.info("BillDAOImpl Search method End");
		return list;
	}

}
