# mamba-sample-project

**Steps to Run the sample Project

1. Clone
2. Run maven verify serenity:aggregate

**To add a new test case

1. Edit src/test/resources/contract-verification.properties file.
    a. Update default-base-url
    b. Add REST specific details for the test case. 
        *.uri - Entry for the url. The default-base-url and uri combination forms the complete endpoint request url. You can provide the complete url in uri field as well.
        *.method - Entry for http methods. It supports all GET, PUT, POST, DELETE http methods. 
        *.query - Entry for query string
        *.body - Entry for request payload in string format. The body should contain the needed escape characters.
        *.header - Entry for header elements. Multiple headers can be separated by comma (,)
        Note: * is the test case key.
    c. Update default-ignore-fields - this contains the dynamic fields which need not be checked, e.g.- current timestamp or corelation-id
2. Add the unit test case java file in src/test/java folder. You can add multiple steps in a single java program and sequence them.
    a. You must add following entries at class level
        @RunWith(MambaRunner.class)
        @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    b. Add feature name at class level 
        @FeatureInfo(name="<a feature name>")
    c. Add following class level variables
        @Rule public ContractAnnotationProcessorRule myRule = new ContractAnnotationProcessorRule();
    d. Add a test case method as below
        @ExtractInfo(extractField = "<attribute name>:<json path in the response>") // this extracts and stores the attribute in a global map to be used in subsequent calls
        @ContractInfo(contractName = "<key identifier>") //here contract name is the key of the rest configuration provided in contract-verification.properties file
        @Test
        @Title("<A title of the test case>") // Title of the test case
        public void test01() {}

