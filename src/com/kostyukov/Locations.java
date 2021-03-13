package com.kostyukov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location>
{
	private static Map<Integer, Location> locations = new HashMap<>();
	
	public static void main(String[] args) throws IOException
	{
		try (FileWriter locFile = new FileWriter("locations.txt");
			FileWriter dirFile = new FileWriter("directions.txt"))
		{
			for (Location locations : locations.values())
			{
				locFile.write(locations.getLocationID() + "," + locations.getDescription() + "\n");
				for (String direction : locations.getExits().keySet())
				{
					dirFile.write(locations.getLocationID() + "," + direction + "," + locations.getExits().get(direction) + "\n");
				}
			}
		}
	}
	
	static
	{
		try (BufferedReader reader = new BufferedReader(new FileReader("res/locations_big.txt")))
		{
			String input;
			while ((input = reader.readLine()) != null)
			{
				String[] data = input.split(",");
				int loc = Integer.parseInt(data[0]);
				String description = data[1];
				
				System.out.println("Imported location: " + loc + ": " + description);
				locations.put(loc, new Location(loc, description));
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader("res/directions_big.txt")))
		{
			String input;
			while ((input = reader.readLine()) != null)
			{
				String[] data = input.split(",");
				int loc = Integer.parseInt(data[0]);
				String direction = data[1];
				int destination = Integer.parseInt(data[2]);
				
				System.out.println(loc + ": " + direction + ": " + destination);
				Location location = locations.get(loc);
				location.addExit(direction, destination);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public int size()
	{
		return locations.size();
	}
	
	@Override
	public boolean isEmpty()
	{
		return locations.isEmpty();
	}
	
	@Override
	public boolean containsKey(Object key)
	{
		return locations.containsKey(key);
	}
	
	@Override
	public boolean containsValue(Object value)
	{
		return locations.containsValue(value);
	}
	
	@Override
	public Location get(Object key)
	{
		return locations.get(key);
	}
	
	@Override
	public Location put(Integer key, Location value)
	{
		return locations.put(key, value);
	}
	
	@Override
	public Location remove(Object key)
	{
		return locations.remove(key);
	}
	
	@Override
	public void putAll(Map<? extends Integer, ? extends Location> m)
	{
	
	}
	
	@Override
	public void clear()
	{
		locations.clear();
	}
	
	@Override
	public Set<Integer> keySet()
	{
		return locations.keySet();
	}
	
	@Override
	public Collection<Location> values()
	{
		return locations.values();
	}
	
	@Override
	public Set<Entry<Integer, Location>> entrySet()
	{
		return locations.entrySet();
	}
}