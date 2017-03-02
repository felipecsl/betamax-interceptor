package walkman

import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Unroll

import static walkman.TapeMode.READ_ONLY
import static walkman.TapeMode.READ_WRITE

@Issue("https://github.com/robfletcher/betamax/issues/106")
@Unroll
class RecorderTapeModeSpec extends Specification {
  void "tape mode is #expectedMode if default mode is #defaultMode and start is called with #modeParam"() {
    given:
    def configuration = WalkmanConfig.builder().defaultMode(defaultMode).build()
    def recorder = new Recorder(configuration, new WalkmanInterceptor())

    when:
    recorder.start("recorder tape mode spec", modeParam)

    then:
    recorder.tape.mode == expectedMode

    where:
    defaultMode | modeParam  | expectedMode
    READ_ONLY   | READ_WRITE | READ_WRITE
  }
}
