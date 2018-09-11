package sapient.storm.demo.bolt;

import static org.junit.Assert.*;

import org.junit.Test;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static sapient.storm.demo.constant.DemoConstants.FIELD_CANDIDATE;
import static sapient.storm.demo.constant.DemoConstants.FIELD_COUNT;
import static sapient.storm.demo.constant.DemoConstants.FIELD_STATE;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CountryTweetCounterBoltTest {

	private Tuple mockCandidateTuple() {
	    Tuple tuple = MockTupleHelpers.mockTuple(FIELD_STATE, FIELD_CANDIDATE, FIELD_COUNT);
	    when(tuple.getValue(0)).thenReturn("TOM");
	    return tuple;
	  }
	
	@Test
	  public void shouldDeclareOutputFields() {
	    // given
	    OutputFieldsDeclarer declarer = mock(OutputFieldsDeclarer.class);
	    CountryTweetCounterBolt bolt = new CountryTweetCounterBolt();

	    // when
	    bolt.declareOutputFields(declarer);

	    // then
	    verify(declarer, times(1)).declare(any(Fields.class));
	  }
	
	 //@Test
	  public void shouldEmitSomethingIfTickTupleIsReceived() {
	    // given
	    Tuple tickTuple = MockTupleHelpers.mockTickTuple();
	    BasicOutputCollector collector = mock(BasicOutputCollector.class);
	    CountryTweetCounterBolt bolt = new CountryTweetCounterBolt();
	    Map<String, Long> counter = new ConcurrentHashMap<String, Long>();
	    
	    // when
	    bolt.execute(tickTuple);

	    // then
	    verifyZeroInteractions(collector);
	   // verify(collector).emit(any(Values.class));
	  }

}
