package demo;

import com.iv.mamba.contracts.framework.filters.ContractAnnotationProcessorRule;
import com.iv.mamba.contracts.framework.filters.ContractInfo;
import com.iv.mamba.contracts.framework.filters.FeatureInfo;
import com.iv.mamba.contracts.framework.runner.MambaRunner;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(MambaRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@FeatureInfo(name="Employee")
public class EmployeeTest {
    @Rule public ContractAnnotationProcessorRule myRule = new ContractAnnotationProcessorRule();

    //@ExtractInfo(extractField = "")
    @ContractInfo(contractName = "employee.get")
    @Test
    @Title("Get All Employees")
    public void test01() {}
}
