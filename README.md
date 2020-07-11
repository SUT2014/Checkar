# Checkar

Checkar is a Selenium based Test module which takes in couple of input files and verifies the accuracy of information by comparing it against a well known database.
Typical Requirements
  - Read Input files and retrieve intelligent test data.  Multiple test input files may be generated.
  - Run Selenium based tests to verify test data.  
  - Retrieve Car Regs from the Input files, retrieve Car information from well known websites and Confirm results with a provided output file.

# Features!

  - Retrieves Car Registration Number from input file.  Multiple input files are supported.
  - Retrieves Verification details of the Car from an output file(CSV format), multiple output files are supported.
  - Using Car Regs from input file, retrieves Car details from well known website, in this case it is https://cartaxcheck.co.uk/
  - Retrieved results are compared against CSV file.  Field level granularity of mismatch is logged.

Checkar has been developed using the following tools/apps:

* [Java] - Core Java 
* [Log4j2] - Logging utility by Apache.
* [IntelliJ] - IDE
* [Selenium] - Firefox Webdriver

And of course Checkar itself is open source on on GitHub.

### Installation

Use Maven to build Checkar.  the pom file can be found along with the source.
Typical Maven commands:
-- mvn clean package

after the packages are build, run the tests using the following command:
-- java -jar <jarfile>

input files:

# car_input.txt
There are multiple websites avaiable to check current car value in United Kingdom.The best of all is webuyanycar.com for intant valuation.The other examples are autotrader.com and confused.com. Checking example BMW with registration DN09HRM the value of the car is roughly around £3000. However car with registration BW57 BOW is not worth much in current market. There are multiple cars available higher than £10k with registraions KT17DLX andSG18 HTN.

# car_output.txt
REGISTRATION,MAKE,MODEL,COLOR,YEAR
SG18HTN,Volkswagen,Golf Se Navigation Tsi Evo,White,20189
DN09HRM,BMW,320D Se,Black,2009
BW57BOF,Toyota,Yaris T2,Black,2010
KT17DLX,Skoda,Superb Sportline Tdi S-A,White,2017


Generated Logs...
00:47:00.999 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - RegNos retrieved from Input File:[DN09HRM, BW57BOW, KT17DLX, SG18HTN]
00:47:01.001 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - ***************************************************************************
00:47:14.535 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Checking Car RegNo: DN09HRM
00:47:14.536 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: BMW
00:47:14.536 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: 320D Se
00:47:14.536 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: Black
00:47:14.536 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: 2009
00:47:14.536 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Passed - RegNo:DN09HRM Car Details Match
00:47:14.536 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Registration,Make,Model,Colour,Year
00:47:14.537 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - DN09HRM,BMW,320D Se,Black,2009,
00:47:14.537 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - ***************************************************************************
00:47:14.537 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - ***************************************************************************
00:47:25.248 [main] ERROR com.github.SUT2014.Checkar.chekar.Checkar - Failed - RegNo:BW57BOW Car details not found
00:47:25.248 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - ***************************************************************************
00:47:25.248 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - ***************************************************************************
00:47:35.824 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Checking Car RegNo: KT17DLX
00:47:35.824 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: Skoda
00:47:35.825 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: Superb Sportline Tdi S-A
00:47:35.825 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: White
00:47:35.825 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: 2017
00:47:35.825 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Passed - RegNo:KT17DLX Car Details Match
00:47:35.825 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Registration,Make,Model,Colour,Year
00:47:35.825 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - KT17DLX,Skoda,Superb Sportline Tdi S-A,White,2017,
00:47:35.825 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - ***************************************************************************
00:47:35.825 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - ***************************************************************************
00:47:48.369 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Checking Car RegNo: SG18HTN
00:47:48.369 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: Volkswagen
00:47:48.369 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: Golf Se Navigation Tsi Evo
00:47:48.369 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Match: White
00:47:48.372 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Items Mismatch: 20189, 2018
00:47:48.372 [main] ERROR com.github.SUT2014.Checkar.chekar.Checkar - Failed - RegNo:SG18HTN Car details Dont Match
00:47:48.372 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - Registration,Make,Model,Colour,Year
00:47:48.372 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - SG18HTN,Volkswagen,Golf Se Navigation Tsi Evo,White,2018,
00:47:48.372 [main] DEBUG com.github.SUT2014.Checkar.chekar.Checkar - ***************************************************************************




**Free Software, Hell Yeah!**
