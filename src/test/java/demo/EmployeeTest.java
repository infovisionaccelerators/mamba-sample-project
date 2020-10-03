package demo;

import com.iv.mamba.contracts.framework.filters.*;
import com.iv.mamba.contracts.framework.runner.MambaRunner;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(MambaRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Feature(name="Employee")
public class EmployeeTest {
    @Rule public ContractAnnotationProcessorRule myRule = new ContractAnnotationProcessorRule();

    @Extract(extractField = "employeeId:data.id")
    @ContractInfo(contractName = "employee.create")
    @Test
    @Title("Create Employee")
    public void test01() {}

    @ContractInfo(contractName = "employee.get")
    @Validations(rules = "status eq filed")
    @Test
    @Title("Get Employee by Id")
    public void test02() {}

    @ContractInfo(contractName = "employee.update")
    @Test
    @Title("Update Employee by Id")
    public void test03() {}

    @ContractInfo(contractName = "employee.get")
    @Test
    @Title("Get Employee by Id")
    public void test04() {}
}
