package elevator;

import java.util.ArrayList;
import java.util.List;

public class Client
{

	public static void main(String[] args)
	{
		Elevator elevator = new Elevator();

		List<Request[]> requests = new ArrayList<Request[]>(16);

		requests.add(new Request[]
		{ new Request(1, 2, 7) });

		requests.add(null);

		requests.add(new Request[]
		{ new Request(2, 6, 5), new Request(3, 4, 8) });
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);
		requests.add(null);

		// 递归处理
		for (int time = 0; time < requests.size(); time++)
		{
			elevator.dealRequests(requests.get(time));
		}
	}
}
