# mamba-sample-project

**Steps to Run the sample Project**

1. Clone
2. Run maven verify serenity:aggregate

**Steps to include Mamba in your existing project a new test case**

1. Include following dependency in pom.xml
        
        <dependency>
            <groupId>com.iv.mamba</groupId>
            <artifactId>mamba-framework-core</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
2. Add below repository to pom.xml

        <repositories>
                <repository>
                    <id>mamba</id>
                    <url>https://github.com/infovisionaccelerators/mamba-dist/raw/mvn-repo/</url>
                    <snapshots>
                        <enabled>true</enabled>
                        <updatePolicy>always</updatePolicy>
                    </snapshots>
                </repository>
        </repositories>
    
3. Include following plugins to <build> tag in pom.xml
        
        <plugin>
            <artifactId>maven-failsafe-plugin</artifactId>
            <version>2.22.1</version>
            <configuration>
                <enableAssertions>false</enableAssertions>
                <includes>
                </includes>
                <parallel>classes</parallel>
                <threadCount>${parallel.tests}</threadCount>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>integration-test</goal>
                        <goal>verify</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>

        <plugin>
            <groupId>net.serenity-bdd.maven.plugins</groupId>
            <artifactId>serenity-maven-plugin</artifactId>
            <version>${serenity.maven.version}</version>
            <configuration>
                <tags>${tags}</tags>
                <reports>email</reports>
            </configuration>
            <executions>
                <execution>
                    <id>serenity-reports</id>
                    <phase>post-integration-test</phase>
                    <goals>
                        <goal>aggregate</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>

4. Edit src/test/resources/contract-verification.properties file.
    
        a. Update default-base-url
        b. Add REST specific details for the test case. 
                *.uri - Entry for the url. The default-base-url and uri combination forms the complete endpoint request url. You can provide the complete url in uri field as well.
                *.method - Entry for http methods. It supports all GET, PUT, POST, DELETE http methods. 
                *.query - Entry for query string
                *.body - Entry for request payload in string format. The body should contain the needed escape characters.
                *.header - Entry for header elements. Multiple headers can be separated by comma (,)
                Note: * is the test case key.
        c. Update default-ignore-fields - this contains the dynamic fields which need not be checked, e.g.- current timestamp or corelation-id

5. Add the unit test case java file in src/test/java folder. You can add multiple steps in a single java program and sequence them.
        
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

