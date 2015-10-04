package elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator
{

    private static final int DOWN_STATE = -1;

    private static final int STOP_STATE = 0;

    private static final int UP_STATE = 1;

    /**
     * 电梯所在楼层
     */
    private int floor;

    /**
     * 电梯运行方向，0表示静止，1表示向上，-1表示向下。
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
        // 第一次接受请求，为时刻1。
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
    	// 此刻如果有请求一并加入outsidePersonList中
        if (requests != null)
        {
            for (Request request : requests)
            {
                outsidePersonList.add(request);
            }
        }
        
        // 如果外面没人，直接返回
        if (!isAnyoneOutside())
        {
            return;
        }

        // 逐一判断
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
    	// 目前楼层必须与起始楼层相同
        if (floor != request.getStartFloor())
        {
            return false;
        }

        // 方向要正确
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
        // 电梯中没人，直接返回
        if (!isAnyoneIn())
        {
            return;
        }

        // 电梯有人，逐一检查是否有人下电梯
        for (Request request : insidePersonList)
        {
            if (floor == request.getEndFloor())
            {
                insidePersonList.remove(request);
            }

            System.out.println("乘客" + request.getPersonID() + "在时刻" + time + "离开电梯。");
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
