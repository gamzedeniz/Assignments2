public class Academician extends Personnel{
    public void totalPay(Academician[] academic, int specialServiceBenefits)
    {
        for(Academician academician : academic) {
            academician.totalSalary += 2600+(2600*specialServiceBenefits/100.0); //adding base salary and ssbenefits to the salary of academicians
        }
    }
}
