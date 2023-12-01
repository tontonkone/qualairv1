export interface AirQuality {
  id: number;
  creationDate: Date;
  updateDate: Date;
  type: 'projection'|'report';
  index: number;
  underIndexesByPollutantId: {[pollutantId: number]: number};
}


export interface Attribution {
  url: string;
  name: string;
  logo: string;
}

export interface City {
  geo: number[];
  name: string;
  url: string;
  location: string;
}

export interface Iaqi {
  dew?: { v: number };
  h?: { v: number };
  no2?: { v: number };
  o3?: { v: number };
  p?: { v: number };
  pm10?: { v: number };
  pm25?: { v: number };
  t?: { v: number };
  w?: { v: number };
}

export interface DailyForecast {
  avg: number;
  day: string;
  max: number;
  min: number;
}

export interface Forecast {
  o3?: DailyForecast[];
  pm10?: DailyForecast[];
  pm25?: DailyForecast[];
}

export interface Time {
  s: string;
  tz: string;
  v: number;
  iso: string;
}

export interface Data {
  aqi: number;
  idx: number;
  attributions: Attribution[];
  city: City;
  dominentpol: string;
  iaqi: Iaqi;
  time: Time;
  forecast: { daily: Forecast };
}

export interface WeatherResponse {
  status: string;
  data: Data;
  debug: { sync: string };
}
