import java.util.*;
import java.io.*;

public class Painting_a_Fence {
	public Offer[] offers;
	public int N;

	public Painting_a_Fence() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		offers = new Offer[N];
		HashMap<String, Integer> colours = new HashMap<>();
		for (int i=0; i<N; i++) {
			String[] in = br.readLine().split(" ");
			String colour = in[0];
			int A = Integer.parseInt(in[1]);
			int B = Integer.parseInt(in[2]);
			
			int C = colours.size();
			if (colours.containsKey(colour)) {
				C = colours.get(colour);
			} else {
				colours.put(colour, C);
			}

			offers[i] = new Offer(C, A, B);
		}

		Arrays.sort(offers);

		//System.out.println(Arrays.toString(offers));

		int next = 1;
		int min = 10000;
		int numColours = Math.max(3, colours.size());
		int[][] colourCombs = new int[choose3(numColours)][3];
		int combCount = 0;
		for (int c1=0; c1<numColours; c1++) {
			for (int c2=c1+1; c2<numColours; c2++) {
				for (int c3=c2+1; c3<numColours; c3++) {
					colourCombs[combCount][0] = c1;
					colourCombs[combCount][1] = c2;
					colourCombs[combCount++][2] = c3;
				}	
			}	
		}
		
		int[] filteredOffers = new int[300];
		for (int comb=0; comb<combCount; comb++) {
			int offerCount = 0;
			for (int i=0; i<N; i++) {
				if (offers[i].colour == colourCombs[comb][0] ||  
					offers[i].colour == colourCombs[comb][1] ||  
					offers[i].colour == colourCombs[comb][2]) {
					
					filteredOffers[offerCount++] = i;
				}
			}
			if (offerCount < 300) {
				filteredOffers[offerCount] = -1;
			}
			min = Math.min(min, paint(filteredOffers));
			if (min == 1) {
				break;
			}
		}
		
		if (min >= 10000) {
			System.out.print("IMPOSSIBLE");
		} else {
			System.out.println(min);
		}
	}

	public int choose3(int n) {
		return n*(n-1)*(n-2)/6;
	}

	public int paint(int[] filteredOffers) {
		int next = 1;
		int index = 0;
		int count = 0;
		int maxValue = -1;
		//System.out.println(filteredOffers);
		while (index < 300 && filteredOffers[index] != -1) {
			int maxIndex = -1;
			maxValue = -1;
			for (int i=index; i < 300 && filteredOffers[i] != -1; i++) {
				Offer offer = offers[filteredOffers[i]];
				if (offer.start <= next) {
					if (offer.end > maxValue) {
						//System.out.println(offer);
						maxValue = offer.end;
						maxIndex = i;
					}
				} else {
					break;
				}
			}
			if (maxIndex == -1) {
				break;
			}
			next = maxValue + 1;
			index = maxIndex + 1;
			count++;
			if (maxValue == 10000) {
				break;
			}
		}
		//Offer maxOffer = filteredOffers.get(maxIndex);
		//min = Math.min(min, paint(filteredOffers, maxOffer.end + 1, maxIndex+1, colours, cur + 1, curMin));

		if (maxValue != 10000) {
			return 10000;
		}

		return count;
	}

	/*
	public int paint(ArrayList<Offer> filteredOffers, int next, int index, int[] colours, int cur, int curMin) {
		if (cur > curMin) {
			return 10000;
		} else if (next > 10000) {
			return cur;
		} else if (index >= filteredOffers.size()) {
			return 10000;
		}

		if (filteredOffers.get(index).start > next) {
			return 10000;
		}

		int maxIndex = index;
		int maxValue = filteredOffers.get(index).end;
		int min = curMin;
		for (int i=index+1; i<filteredOffers.size(); i++) {
			Offer offer = filteredOffers.get(i);
			if (offer.start <= next) {
				if (maxValue < offer.end) {
					maxValue = offer.end;
					maxIndex = i;
				}
			} else {
				break;
			}
		}
		Offer maxOffer = filteredOffers.get(maxIndex);
		min = Math.min(min, paint(filteredOffers, maxOffer.end + 1, maxIndex+1, colours, cur + 1, curMin));

		return min;
	}
	*/

	public static void main(String[] args) throws Exception  {
		new Painting_a_Fence();
	}

	private class Offer implements Comparable<Offer> {
		int colour;
		int start;
		int end;

		public Offer(int colour, int start, int end) {
			this.colour = colour;
			this.start = start;
			this.end = end;
		}

		public int compareTo(Offer other) {
			if (start == other.start) {
				return other.end - end;
			}
			return start - other.start;
		}

		public String toString() {
			return ""+start+":"+end;
		}
	}
}