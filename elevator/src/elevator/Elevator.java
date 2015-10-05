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
	 * 电梯运行方向，1表示向上，0表示停止，-1表示向下
	 */
	private int direction;

	/**
	 * 上一个电梯运行方向
	 */
	private int preDirection;

	private int time;

	private List<Request> insidePersonList;

	private List<Request> outsidePersonList;

	public Elevator()
	{
		floor = 1;
		direction = 0;
		preDirection = 0;
		time = 0;
		insidePersonList = new ArrayList<>(16);
		outsidePersonList = new ArrayList<>(16);
	}

	public void dealRequests(Request... requests)
	{
		// 记录状态
		preDirection = direction;

		// 从0时刻开始计时间
		time++;

		isAnyoneWantLeave();
		isAnyoneWantIn(requests);
		chooseDirection();
		move();
	}

	private void chooseDirection()
	{
		// 如果电梯正在运行，不改变它的运行方向，如果有人上下电梯，电梯停止
		// 这时我们再选择电梯的下一步运行方向。
		if (direction != STOP_STATE)
		{
			return;
		}

		// 如果电梯为静止
		if (preDirection == STOP_STATE)
		{
			// 里外都没人，直接返回，电梯继续静止
			if (!isAnyoneInside() && !isAnyoneOutside())
			{
				return;
			}

			// 少数服从多数
			chooseDirectionByCount(countUpInside() + countUpOutside(),
					countDownInside() + countDownOutside());
		}
		else if (preDirection == UP_STATE)
		{
			// 优先满足原方向
			if (isUpRequestInSide() || isUpRequestOutSide())
			{
				direction = UP_STATE;
				return;
			}

			if (isDownRequestInSide() || isDownRequestOutSide())
			{
				direction = DOWN_STATE;
			}

		}
		else
		{
			// 优先满足原有方向
			if (isDownRequestInSide() || isDownRequestOutSide())
			{
				direction = DOWN_STATE;
				return;
			}

			if (isUpRequestInSide() || isUpRequestOutSide())
			{
				direction = UP_STATE;
			}
		}
	}

	private boolean isUpRequestInSide()
	{
		boolean isRequest = false;

		if (isAnyoneInside())
		{
			for (Request request : insidePersonList)
			{
				if (floor < request.getEndFloor())
				{
					isRequest = true;
					break;
				}
			}
		}

		return isRequest;
	}

	private boolean isDownRequestInSide()
	{
		boolean isRequest = false;

		if (isAnyoneInside())
		{
			for (Request request : insidePersonList)
			{
				if (floor > request.getEndFloor())
				{
					isRequest = true;
					break;
				}
			}
		}

		return isRequest;
	}

	private void chooseDirectionByCount(int upCount, int downCount)
	{
		if (upCount >= downCount)
		{
			direction = UP_STATE;
		}
		else
		{
			direction = DOWN_STATE;
		}
	}

	private int countDownOutside()
	{
		int count = 0;

		if (isAnyoneOutside())
		{
			for (Request request : outsidePersonList)
			{
				if (floor > request.getStartFloor())
				{
					count++;
				}
			}
		}

		return count;
	}

	private int countUpOutside()
	{
		int count = 0;

		if (isAnyoneOutside())
		{
			for (Request request : outsidePersonList)
			{
				if (floor < request.getStartFloor())
				{
					count++;
				}
			}
		}

		return count;
	}

	private int countDownInside()
	{
		int count = 0;

		if (isAnyoneInside())
		{
			for (Request request : insidePersonList)
			{
				if (floor > request.getEndFloor())
				{
					count++;
				}
			}
		}

		return count;
	}

	private int countUpInside()
	{
		int count = 0;

		if (isAnyoneInside())
		{
			for (Request request : insidePersonList)
			{
				if (floor < request.getEndFloor())
				{
					count++;
				}
			}
		}

		return count;
	}

	private boolean isDownRequestOutSide()
	{
		boolean isRequest = false;

		if (isAnyoneOutside())
		{
			for (Request request : outsidePersonList)
			{
				if (floor > request.getStartFloor())
				{
					isRequest = true;
					break;
				}
			}
		}

		return isRequest;
	}

	private boolean isUpRequestOutSide()
	{
		boolean isRequest = false;

		if (isAnyoneOutside())
		{
			for (Request request : outsidePersonList)
			{
				if (floor < request.getStartFloor())
				{
					isRequest = true;
					break;
				}
			}
		}

		return isRequest;
	}

	private void move()
	{
		if (direction == UP_STATE)
		{
			floor++;
		}
		else if (direction == DOWN_STATE)
		{
			floor--;
		}
	}

	private void isAnyoneWantIn(Request... requests)
	{
		// 将请求逐个加入outsidePersonList中
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

		// 逐一判断是否进电梯
		List<Request> outsideTempList = new ArrayList<Request>(
				outsidePersonList);

		for (Request request : outsideTempList)
		{
			if (isPersonShouldIn(request))
			{
				direction = STOP_STATE;
				insidePersonList.add(request);
				outsidePersonList.remove(request);
			}
		}
	}

	private boolean isPersonShouldIn(Request request)
	{
		// 判断起始楼层是否相符
		if (floor != request.getStartFloor())
		{
			return false;
		}

		// 方向是否正确
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
		// 如果电梯里没人，直接返回
		if (!isAnyoneInside())
		{
			return;
		}

		// 逐一判断是否有人下电梯
		List<Request> insideTempList = new ArrayList<Request>(insidePersonList);

		for (Request request : insideTempList)
		{
			if (floor == request.getEndFloor())
			{
				insidePersonList.remove(request);

				System.out.println("乘客" + request.getPersonID() + "在时间" + time
						+ "离开电梯");
			}
		}

		// 如果电梯里没人，停止电梯
		if (!isAnyoneInside())
		{
			direction = STOP_STATE;
		}
	}

	private boolean isAnyoneInside()
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
