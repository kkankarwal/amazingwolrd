package sapient.storm.demo.bolt;

import backtype.storm.Constants;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static sapient.storm.demo.constant.DemoConstants.FIELD_CANDIDATE;
import static sapient.storm.demo.constant.DemoConstants.FIELD_COUNT;
import static sapient.storm.demo.constant.DemoConstants.FIELD_STATE;

public final class MockTupleHelpers {

	  private MockTupleHelpers() {
	  }

	  public static Tuple mockTickTuple() {
		  	    return mockTuple(FIELD_STATE, FIELD_CANDIDATE, FIELD_COUNT);
	  }

	  public static Tuple mockTuple(String state, String candidate, String count) {
	    Tuple tuple = mock(Tuple.class);
	    when(tuple.getSourceComponent()).thenReturn(state);
	    when(tuple.getSourceStreamId()).thenReturn(candidate);
	    when(tuple.getStringByField(anyString())).thenReturn(candidate);
	    return tuple;
	  }
	}