package elevator;

import java.util.ArrayList;
import java.util.List;

public class Client
{

    public static void main(String[] args)
    {
        Elevator elevator = new Elevator();

        List<Request[]> requests = new ArrayList<Request[]>(16);

        // ĳһʱ�̿����ж������Ҳ����û������requests�������ǰ���ʱ��˳��ȫ����װ��������������ִ�С�
        for (int time = 0; time < requests.size(); time++)
        {
            elevator.dealRequests(requests.get(time));
        }
    }
}
