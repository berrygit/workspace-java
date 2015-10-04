package elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator
{

    private static final int DOWN_STATE = -1;

    private static final int STOP_STATE = 0;

    private static final int UP_STATE = 1;

    /**
     * ��������¥��
     */
    private int floor;

    /**
     * �������з���0��ʾ��ֹ��1��ʾ���ϣ�-1��ʾ���¡�
     */
    private int direction;

    private int time;

    private List<Request> insidePersonList;

    private List<Request> outsidePersonList;

    public Elevator()
    {
        floor = 1;
        direction = 0;
        time = 0;
        insidePersonList = new ArrayList<>(16);
        outsidePersonList = new ArrayList<>(16);
    }

    public void dealRequests(Request... requests)
    {
        // ��һ�ν�������Ϊʱ��1��
        time++;

        isAnyoneWantLeave();
        isAnyoneWantIn(requests);
        chooseDirection();
        move();
    }

    private void chooseDirection()
    {
    	if(!isAnyoneIn()&&!isAnyoneOutside())
    	{
    		direction=STOP_STATE;
    	}
    	
    	
    }

    private void move()
    {
    }

    private void isAnyoneWantIn(Request... requests)
    {
    	// �˿����������һ������outsidePersonList��
        if (requests != null)
        {
            for (Request request : requests)
            {
                outsidePersonList.add(request);
            }
        }
        
        // �������û�ˣ�ֱ�ӷ���
        if (!isAnyoneOutside())
        {
            return;
        }

        // ��һ�ж�
        for (Request request : outsidePersonList)
        {
            if (isPersonShouldIn(request))
            {
                insidePersonList.add(request);
                outsidePersonList.remove(request);
            }
        }
    }

    private boolean isPersonShouldIn(Request request)
    {
    	// Ŀǰ¥���������ʼ¥����ͬ
        if (floor != request.getStartFloor())
        {
            return false;
        }

        // ����Ҫ��ȷ
        if (isPassageWantUp(request) && direction != DOWN_STATE)
        {
            return true;
        }
        else if (isPassageWantDown(request) && direction != UP_STATE)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    private void isAnyoneWantLeave()
    {
        // ������û�ˣ�ֱ�ӷ���
        if (!isAnyoneIn())
        {
            return;
        }

        // �������ˣ���һ����Ƿ������µ���
        for (Request request : insidePersonList)
        {
            if (floor == request.getEndFloor())
            {
                insidePersonList.remove(request);
            }

            System.out.println("�˿�" + request.getPersonID() + "��ʱ��" + time + "�뿪���ݡ�");
        }
    }

    private boolean isAnyoneIn()
    {
        if (insidePersonList == null || insidePersonList.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean isAnyoneOutside()
    {
        if (outsidePersonList == null || outsidePersonList.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean isPassageWantUp(Request request)
    {
        return request.getStartFloor() < request.getEndFloor();
    }

    private boolean isPassageWantDown(Request request)
    {
        return request.getStartFloor() > request.getEndFloor();
    }

}
