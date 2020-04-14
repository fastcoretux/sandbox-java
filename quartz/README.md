# Quartz monitor

Simple sandbox for testing quartz jobs.

### Monitoring
* by [qrtzjobs](http://localhost:8080/actuator/qrtzjobs) Spring Boot Actuator Endpoint
* by simple static page [job.html](http://localhost:8080/job.html)

### Control
* by [qrtzjobs](http://localhost:8080/actuator/qrtzjobs) endpoint (start/stop) all jobs

`POST http://localhost:8080/actuator/qrtzjobs`
```json
{
	"operation":"start"
}
```
 