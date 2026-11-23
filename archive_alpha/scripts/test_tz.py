import datetime
ts = '2026-11-22T04:45:08.901Z'
dt_utc = datetime.datetime.fromisoformat(ts.replace('Z', '+00:00'))
dt_local = dt_utc.astimezone().replace(tzinfo=None)
print('UTC:', dt_utc)
print('Local:', dt_local)
print('Hour local:', dt_local.hour, 'Minute local:', dt_local.minute)
