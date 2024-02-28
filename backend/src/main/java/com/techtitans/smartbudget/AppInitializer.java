package com.techtitans.smartbudget;

import com.techtitans.smartbudget.dataManagement.MainDataManagement;
import com.techtitans.smartbudget.model.Privilege;
import com.techtitans.smartbudget.model.Role;
import com.techtitans.smartbudget.repository.PrivilegeRepository;
import com.techtitans.smartbudget.repository.RoleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.techtitans.smartbudget.model.enums.PrivilegeName.*;
import static com.techtitans.smartbudget.model.enums.RoleName.*;

@Component
public class AppInitializer {
    private final MainDataManagement mainDataManagement;
    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository;

    public AppInitializer(MainDataManagement mainDataManagement, PrivilegeRepository privilegeRepository,
                          RoleRepository roleRepository) {
        this.mainDataManagement = mainDataManagement;
        this.privilegeRepository = privilegeRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void postConstruct() {
        setupDatabase();
//        mainDataManagement.runInBackground();
    }

    private void setupDatabase() {
        if (roleRepository.findAll().isEmpty()) {
            populateRolesAndPrivileges();
        }
    }

    private void populateRolesAndPrivileges() {
        List<Privilege> adminPrivileges = Arrays.asList(
                new Privilege(VIEW_USER_ACCOUNTS),
                new Privilege(MODIFY_USER_ACCOUNTS),
                new Privilege(VIEW_BANK_ACCOUNTS),
                new Privilege(MODIFY_BANK_ACCOUNTS),
                new Privilege(VIEW_TRANSACTIONS),
                new Privilege(MODIFY_TRANSACTIONS),
                new Privilege(VIEW_EXPENSE_CATEGORIES),
                new Privilege(MODIFY_EXPENSE_CATEGORIES),
                new Privilege(VIEW_BUDGETS),
                new Privilege(MODIFY_BUDGETS),
                new Privilege(VIEW_SAVING_GOALS),
                new Privilege(MODIFY_SAVING_GOALS),
                new Privilege(VIEW_INVESTMENT_RECOMMENDATIONS),
                new Privilege(MODIFY_INVESTMENT_RECOMMENDATIONS),
                new Privilege(VIEW_COMPANY_DATA),
                new Privilege(MODIFY_COMPANY_DATA),
                new Privilege(VIEW_AI_SETTINGS),
                new Privilege(MODIFY_AI_SETTINGS),
                new Privilege(VIEW_FINANCIAL_PREDICTIONS_SETTINGS),
                new Privilege(MODIFY_FINANCIAL_PREDICTIONS_SETTINGS));

        List<Privilege> userPrivileges = Arrays.asList(
                new Privilege(VIEW_OWN_BANK_ACCOUNT),
                new Privilege(MODIFY_OWN_BANK_ACCOUNT),
                new Privilege(VIEW_OWN_TRANSACTIONS),
                new Privilege(MODIFY_OWN_TRANSACTIONS),
                new Privilege(VIEW_OWN_TRANSACTION_HISTORY),
                new Privilege(VIEW_OWN_BUDGET),
                new Privilege(MODIFY_OWN_BUDGET),
                new Privilege(VIEW_OWN_SAVING_GOALS),
                new Privilege(MODIFY_OWN_SAVING_GOALS));
        persistPrivileges(adminPrivileges, userPrivileges);

        Role admin = new Role(ADMIN, adminPrivileges);
        Role user = new Role(USER, userPrivileges);
        Role guest = new Role(GUEST);
        roleRepository.saveAll(Arrays.asList(admin, user, guest));
    }

    private void persistPrivileges(List<Privilege> adminPrivileges, List<Privilege> userPrivileges) {
        List<Privilege> privileges = new ArrayList<>();
        privileges.addAll(adminPrivileges);
        privileges.addAll(userPrivileges);
        privilegeRepository.saveAll(privileges);
    }
}
