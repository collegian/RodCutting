package bottom.up;

import java.util.HashMap;
import java.util.Map;

public class BottomUpRodCutting
{
	private final Map<Integer, Integer> pricesByLength;
	private Map<Integer, Integer> maxRevenueByLength;

	public BottomUpRodCutting(Map<Integer, Integer> pricesByLength)
	{
		this.pricesByLength = pricesByLength;
	}

	public int getMaxRevenue(int n)
	{
		maxRevenueByLength = new HashMap<>(pricesByLength.size());
		for (int i = 1; i <= n; i++)
		{
			int q = Integer.MIN_VALUE;
			for (int j = 1; j <= i; j++)
			{
				Integer price = pricesByLength.get(j);
				if (price != null)
				{
					Integer maxRevenue = maxRevenueByLength.get(i - j);
					if (maxRevenue != null)
					{
						q = Math.max(q, price + maxRevenue);
					}
					else
					{
						q = Math.max(q, price);
					}
				}
			}

			maxRevenueByLength.put(i, q);
		}

		Integer maxRev = maxRevenueByLength.get(n);

		if (maxRev != null)
			return maxRev;
		return 0;
	}

	public static void main(String[] args)
	{
		Map<Integer, Integer> pricesByLength = new HashMap<>();
		pricesByLength.put(1, 1);
		pricesByLength.put(2, 5);
		pricesByLength.put(3, 8);
		pricesByLength.put(4, 9);
		pricesByLength.put(5, 10);
		pricesByLength.put(6, 17);
		pricesByLength.put(7, 17);
		pricesByLength.put(8, 20);
		pricesByLength.put(9, 24);
		pricesByLength.put(10, 30);
		BottomUpRodCutting rod = new BottomUpRodCutting(pricesByLength);
		System.out.println(rod.getMaxRevenue(9));
	}
}
