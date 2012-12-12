package service;

import java.util.Date;

import model.Contract;
import model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ContractDao;
import dao.RoleUserDao;

@Service
public class ContractService {

	@Autowired
	private ContractDao contractDao;
	@Autowired
	private RoleUserDao roleUserDao;

	public Contract get(Long cntId) {
		return contractDao.select(cntId);
	}

	public Long add(Contract contract) {
		Role role = getRole(1l);

		initAdd(contract, role);
		initModify(contract, role);

		contractDao.insert(contract);
		return contract.getCntId();
	}

	public void draft(Contract contract) {
		Role role = getRole(1l);

		contract.setStatus(Contract.CONTRACT_DRAFT);
		contract.setDrafter(role);
		initModify(contract, role);

		contractDao.update(contract);
	}

	public void submit(Contract contract) {
		Role role = getRole(2l);

		Contract dbContract = contractDao.select(contract.getCntId());
		dbContract.setStatus(Contract.CONTRACT_SUBMIT);
		dbContract.setSubmiter(role);
		initModify(dbContract, role);

		contractDao.update(dbContract);
	}

	public void approve(Contract contract) {
		Role role = getRole(3l);

		Contract dbContract = contractDao.select(contract.getCntId());
		dbContract.setStatus(Contract.CONTRACT_APPROVE);
		dbContract.setChecker(role);
		initModify(dbContract, role);

		contractDao.update(dbContract);
	}

	public void reject(Contract contract) {
		Role role = getRole(3l);

		Contract dbContract = contractDao.select(contract.getCntId());
		dbContract.setStatus(Contract.CONTRACT_REJECT);
		dbContract.setChecker(role);
		initModify(dbContract, role);

		contractDao.update(dbContract);
	}

	public void modify(Contract contract) {
		Role role = getRole(1l);

		Contract dbContract = contractDao.select(contract.getCntId());
		dbContract.setStatus(Contract.CONTRACT_DRAFT);
		dbContract.setDrafter(role);
		initModify(dbContract, role);

		contractDao.update(dbContract);
	}

	public void repeal(Contract contract) {
		Role role = getRole(3l);

		Contract dbContract = contractDao.select(contract.getCntId());
		dbContract.setStatus(Contract.CONTRACT_REPEAL);
		dbContract.setRepealer(role);
		initModify(dbContract, role);

		contractDao.update(dbContract);
	}

	private void initAdd(Contract contract, Role role) {
		contract.setCreator(role);
		contract.setCreatedTime(new Date());
	}

	private void initModify(Contract contract, Role role) {
		contract.setModifier(role);
		contract.setModifiedTime(new Date());
	}

	// 当前登录人可以在我们系统中的threadlocal 中直接获取的。在此简便不模拟了
	private Role getRole(Long roleId) {
		return roleUserDao.select(roleId);
	}

}
